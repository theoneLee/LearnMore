package LearnMore.controller;

import LearnMore.entity.Course;
import LearnMore.entity.Question;
import LearnMore.entity.Response;
import LearnMore.entity.wrapper.CommonUserWrapper;
import LearnMore.entity.wrapper.CourseWrapper;
import LearnMore.security.CheckPermission;
import LearnMore.security.IgnoreSecurity;
import LearnMore.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Lee on 2017/6/3 0003.
 */
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/course",method = RequestMethod.GET,produces = "application/json")
    @IgnoreSecurity
    @CrossOrigin
    public Response getAllCourse(){
        List<Course> list=courseService.getAllCourse();
        //System.out.println("courseList:"+list.get(0).getCourseContentList());
        return new Response().success(list);
    }
    @RequestMapping(value = "/course/{id}",method =RequestMethod.GET,produces = "application/json")
    @IgnoreSecurity
    @CrossOrigin
    public Response getCourseById(@PathVariable(name = "id")Integer id){
        Course course=courseService.getCourseById(id);
        return new Response().success(course);
    }


    @RequestMapping(value = "/course/save",method = RequestMethod.POST)//这里要验证token和permission
    //@CheckPermission
    @IgnoreSecurity
    @CrossOrigin
    public Response getFormData(CourseWrapper courseWrapper)throws Exception{//todo 课程基本信息和课程内容是分开提交的，类似于新闻分类和新闻那样子；在新建课程内容是可以选这个课程内容是属于那一个课程的
        //这里只处理课程基本信息
        // 文件通过Question模版转化为json，视频保存后返回一个路径 封装成courseContent,然后通过courseName来获取course，再做关联并持久化course
        List<Question> examJson=courseService.getJson(courseWrapper.getExam());
        Course course=new Course();
        course.setCourseName(courseWrapper.getCourseName());
        course.setTeacherTeam(courseWrapper.getTeacherTeam());
        course.setCourseIntroduction(courseWrapper.getCourseIntroduction());
        course.setCourseOutline(courseWrapper.getCourseOutline());
        course.setCourseExamJson(examJson);

        courseService.saveAdd(course);
        return new Response().success();
    }
    @PostMapping("/course/save/test")
    @IgnoreSecurity
    @CrossOrigin
    public void getFormDataTest()throws Exception{
        CourseWrapper wrapper=new CourseWrapper();
        wrapper.setCourseName("CourseName2");
        wrapper.setCourseIntroduction("Introduction2");
        wrapper.setCourseOutline("CourseOutline2");
        wrapper.setTeacherTeam("TeacherTeam2");
        List<Question> examJson=new ArrayList<>();
        for (int i=0;i<10;i++){
            Question q=new Question();
            q.setNumber(i);
            q.setTitle("title"+i);
            q.setAnswer("answer"+i);
            examJson.add(q);
        }

        Course course=new Course();
        course.setCourseName(wrapper.getCourseName());
        course.setTeacherTeam(wrapper.getTeacherTeam());
        course.setCourseIntroduction(wrapper.getCourseIntroduction());
        course.setCourseOutline(wrapper.getCourseOutline());
        course.setCourseExamJson(examJson);

        courseService.saveAdd(course);
    }

    /**
     * 执行更新Course操作
     * 在提交这个表单前要先把数据事先取出来并填充到表单中供人检验
     * @param courseName
     * @param teacherTeam
     * @param courseIntroduction
     * @param courseOutline
     * @param exam
     * @param id
     * @return
     */
    @RequestMapping(value = "/course/update/{id}",method = RequestMethod.POST)//这里要验证token和permission
    //@CheckPermission
    @IgnoreSecurity
    @CrossOrigin
    public Response updateFormData(@RequestParam("courseName")String courseName,
                                @RequestParam("teacherTeam")String teacherTeam,
                                @RequestParam("courseIntroduction")String courseIntroduction,
                                @RequestParam("courseOutline")String courseOutline,
                                @RequestParam("exam") MultipartFile exam,
                                @PathVariable(name = "id")Integer id) throws Exception{//todo 课程基本信息和课程内容是分开提交的，类似于新闻分类和新闻那样子；在新建课程内容是可以选这个课程内容是属于那一个课程的
        List<Question> examJson=courseService.getJson(exam);
        Course course=courseService.getCourseByIdFetchExam(id);
        course.setCourseName(courseName);
        course.setTeacherTeam(teacherTeam);
        course.setCourseIntroduction(courseIntroduction);
        course.setCourseOutline(courseOutline);
        course.getCourseExamJson().clear();//清掉原来的数据
        List<Question> courseCourseExam=course.getCourseExamJson();
        courseCourseExam.addAll(examJson);//重新添加题目

        courseService.save(course);
        return new Response().success();
    }

    @PostMapping("/course/update/{id}/test")
    public void updateFormDataTest(@PathVariable(name = "id")Integer id){


    }


    @RequestMapping(value = "/course/delete/{id}",method = RequestMethod.GET)
    //@CheckPermission
    @IgnoreSecurity
    @CrossOrigin
    public Response deleteCourseById(@PathVariable(name = "id")Integer id){
        courseService.deleteCourseById(id);
        return new Response().success();
    }


    @RequestMapping(value = "/course/name/{courseName}",method = RequestMethod.GET)
    @IgnoreSecurity
    @CrossOrigin
    public Response getCourseByCourseName(@PathVariable(name = "courseName")String courseName){
        Course course=courseService.getCourseByName(courseName);
        return new Response().success(course);
    }


    /**
     * 找到修某一门课的全部成员名字
     * @param courseName
     * @return
     */
    @RequestMapping(value = "/course/userList",method = RequestMethod.GET)
    @IgnoreSecurity
    @CrossOrigin
    public Response getCourseUserListByCourseName(String courseName){
        List<String> list=courseService.getCourUserListByCourseName(courseName);

        return new Response().success(list);
    }
}
