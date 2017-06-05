package LearnMore.controller;

import LearnMore.entity.Course;
import LearnMore.entity.Question;
import LearnMore.entity.Response;
import LearnMore.security.CheckPermission;
import LearnMore.security.IgnoreSecurity;
import LearnMore.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 *
 * Created by Lee on 2017/6/3 0003.
 */
@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/course",method = RequestMethod.GET)
    @IgnoreSecurity
    public Response getAllCourse(){
        List<Course> list=courseService.getAllCourse();
        return new Response().success(list);
    }
    @RequestMapping(value = "/course/{id}",method =RequestMethod.GET)
    @IgnoreSecurity
    public Response getCourseById(@PathVariable(name = "id")Integer id){
        Course course=courseService.getCourseById(id);
        return new Response().success(course);
    }


    @RequestMapping(value = "/course/save",method = RequestMethod.POST)//这里要验证token和permission
    @CheckPermission
    public Response getFormData(String courseName,
                                String teacherTeam,
                                String courseIntroduction,
                                String courseOutline,
                                @RequestParam(value = "exam") MultipartFile exam)throws IOException{//todo 课程基本信息和课程内容是分开提交的，类似于新闻分类和新闻那样子；在新建课程内容是可以选这个课程内容是属于那一个课程的
        //这里只处理课程基本信息
        // 文件通过Question模版转化为json，视频保存后返回一个路径 封装成courseContent,然后通过courseName来获取course，再做关联并持久化course
        System.out.println("examFile:"+exam.getOriginalFilename());
        List<Question> examJson=courseService.getJson(exam);
        Course course=new Course();
        course.setCourseName(courseName);
        course.setTeacherTeam(teacherTeam);
        course.setCourseIntroduction(courseIntroduction);
        course.setCourseOutline(courseOutline);
        course.setCourseExamJson(examJson);

        courseService.save(course);
        return new Response().success();
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
    @CheckPermission
    public Response updateFormData(@RequestParam("courseName")String courseName,
                                @RequestParam("teacherTeam")String teacherTeam,
                                @RequestParam("courseIntroduction")String courseIntroduction,
                                @RequestParam("courseOutline")String courseOutline,
                                @RequestParam("exam") MultipartFile exam,
                                @PathVariable(name = "id")Integer id) throws IOException{//todo 课程基本信息和课程内容是分开提交的，类似于新闻分类和新闻那样子；在新建课程内容是可以选这个课程内容是属于那一个课程的
        List<Question> examJson=courseService.getJson(exam);
        Course course=courseService.getCourseById(id);
        course.setCourseName(courseName);
        course.setTeacherTeam(teacherTeam);
        course.setCourseIntroduction(courseIntroduction);
        course.setCourseOutline(courseOutline);
        course.setCourseExamJson(examJson);

        courseService.save(course);
        return new Response().success();
    }

//    @RequestMapping(value ="/update/{id}",method = RequestMethod.GET)
//    public Response getFormData(@PathVariable(name = "id")Integer id){
//        Course course=courseService.getCourseById(id);
//        return new Response().success(course);
//    }

    @RequestMapping(value = "/course/delete/{id}",method = RequestMethod.GET)
    @CheckPermission
    public Response deleteCourseById(@PathVariable(name = "id")Integer id){
        courseService.deleteCourseById(id);
        return new Response().success();
    }


}
