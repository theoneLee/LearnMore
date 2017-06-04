package LearnMore.controller;

import LearnMore.entity.Course;
import LearnMore.entity.CourseContent;
import LearnMore.entity.Question;
import LearnMore.entity.Response;
import LearnMore.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Lee on 2017/6/4 0004.
 */
@RestController(value = "/courseContent")
public class CourseContentController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Response addCourseContent(@RequestParam("courseContentName")String courseContentName,@RequestParam("courseName")String courseName,
                                     @RequestParam("video") MultipartFile video,@RequestParam("homework") MultipartFile homework
                                     ){
        //接受表单的字段，还有接受表单包含的文件(第一个是视频内容，第二个是作业)
        //字段，文件通过Question模版转化为json，视频保存后返回一个路径 封装成courseContent,然后通过courseName来获取course，再做关联并持久化course
        //文件格式要求：一行题目一行选项（以换行符为准），空行不记
        List<Question> homeworkJson=courseService.getJson(homework);
        String videoLink=courseService.getLinkByVideo(video);

        CourseContent courseContent=new CourseContent();//封装
        courseContent.setCourseContentName(courseContentName);
        courseContent.setCourseVideoLink(videoLink);
        courseContent.setCourseHomeWorkJson(homeworkJson);

        Course course=courseService.getCourseByName(courseName);

        courseContent.setCourse(course);//关联
        course.getCourseContentList().add(courseContent);

        courseService.save(course);//持久化course，通过级联持久化CourseConetnt
    }

}
