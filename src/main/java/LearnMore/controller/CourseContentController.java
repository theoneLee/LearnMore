package LearnMore.controller;

import LearnMore.entity.Course;
import LearnMore.entity.CourseContent;
import LearnMore.entity.Question;
import LearnMore.entity.Response;
import LearnMore.entity.wrapper.CourseContentWrapper;
import LearnMore.security.CheckPermission;
import LearnMore.security.IgnoreSecurity;
import LearnMore.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lee on 2017/6/4 0004.
 */
@RestController
public class CourseContentController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/courseContent/save",method = RequestMethod.POST)
    @CheckPermission
    public Response addCourseContent(CourseContentWrapper courseContentWrapper)throws IOException {
        //接受表单的字段，还有接受表单包含的文件(第一个是视频内容，第二个是作业)
        //字段，文件的每一道题目通过Question模版转化，视频保存后返回一个路径 封装成courseContent,然后通过courseName来获取course，再做关联并持久化course
        //文件格式要求：一行题目一行选项（以换行符为准），空行不记
        List<Question> homeworkJson=courseService.getJson(courseContentWrapper.getHomework());
        String videoLink=courseService.getLinkByVideo(courseContentWrapper.getVideo(),courseContentWrapper.getCourseContentName());

        CourseContent courseContent=new CourseContent();//封装
        courseContent.setCourseContentName(courseContentWrapper.getCourseContentName());
        courseContent.setCourseVideoLink(videoLink);
        courseContent.setCourseHomeWorkJson(homeworkJson);

        Course course=courseService.getCourseByNameFetchCourseContentList(courseContentWrapper.getCourseName());//要一并fetch到CourseContentList，否则后面关联时无法正常添加CourseContent

        courseContent.setCourse(course);//关联
        course.getCourseContentList().add(courseContent);

        courseService.save(course);//持久化course，通过级联持久化CourseConetnt
        return new Response().success();
    }

    @PostMapping("/courseContent/save/test")
    public void addCourseContentTest(){
        List<Question> homeworkJson=new ArrayList<>();
        for (int i=0;i<2;i++){
            Question q=new Question();
            q.setNumber(i);
            q.setTitle("title"+i);
            q.setAnswer("answer"+i);
            homeworkJson.add(q);
        }
        String videoLink="/vedio/homework.mp4";
        CourseContent courseContent=new CourseContent();//封装
        courseContent.setCourseContentName("CourseName1");
        courseContent.setCourseVideoLink(videoLink);
        courseContent.setCourseHomeWorkJson(homeworkJson);

        Course course=courseService.getCourseByNameFetchCourseContentList("CourseName1");

        courseContent.setCourse(course);//关联
        course.getCourseContentList().add(courseContent);

        courseService.save(course);//持久化course，通过级联持久化CourseConetnt
    }

    @RequestMapping(value = "/courseContent/{id}",method =RequestMethod.GET)
    @IgnoreSecurity
    public Response getCourseContentById(@PathVariable(name = "id")Integer id){
        CourseContent courseContent=courseService.getCourseContentByccId(id);
        return new Response().success(courseContent);
    }

    @RequestMapping(value ="/courseContent",method =RequestMethod.GET)
    @IgnoreSecurity
    public Response getAllCourseContent(){
        List<CourseContent> list=courseService.getAllCourseContent();
        return new Response().success(list);
    }

    @RequestMapping(value ="/courseContent/course/{courseName}",method =RequestMethod.GET)
    @IgnoreSecurity
    public Response getAllCourseContentByCourse(@PathVariable("courseName")String courseName){
        List<CourseContent> list=courseService.getCourseContentListByCourse(courseName);
        return new Response().success(list);
    }

    @RequestMapping(value = "/courseContent/delete/{id}",method = RequestMethod.GET)
    @CheckPermission
    public Response deleteCourseContentById(@PathVariable(name = "id")Integer id){
        courseService.deleteCourseContentById(id);
        return new Response().success();
    }


}
