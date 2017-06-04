package LearnMore.controller;

import LearnMore.entity.Course;
import LearnMore.entity.Question;
import LearnMore.entity.Response;
import LearnMore.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * Created by Lee on 2017/6/3 0003.
 */
@RestController(value = "/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/")
    public Response getAllCourse(){
        List<Course> list=courseService.getAllCourse();//这里的课程

    }

    @RequestMapping(value = "/",method = RequestMethod.POST)//这里要验证token和permission
    public Response getFormData(@RequestParam("courseName")String courseName,
                                @RequestParam("teacherTeam")String teacherTeam,
                                @RequestParam("courseIntroduction")String courseIntroduction,
                                @RequestParam("courseOutline")String courseOutline,
                                @RequestParam("exam") MultipartFile exam){//todo 课程基本信息和课程内容是分开提交的，类似于新闻分类和新闻那样子；在新建课程内容是可以选这个课程内容是属于那一个课程的
        //这里只处理课程基本信息
        // 文件通过Question模版转化为json，视频保存后返回一个路径 封装成courseContent,然后通过courseName来获取course，再做关联并持久化course
        List<Question> examJson=courseService.getJson(exam);
        Course course=new Course();
        course.setCourseName(courseName);
        course.setTeacherTeam(teacherTeam);
        course.setCourseIntroduction(courseIntroduction);
        course.setCourseOutline(courseOutline);
        course.setCourseExamJson(examJson);

        courseService.save(course);
    }





}
