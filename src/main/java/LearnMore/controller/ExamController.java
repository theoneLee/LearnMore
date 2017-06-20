package LearnMore.controller;

import LearnMore.entity.Response;
import LearnMore.entity.wrapper.ExamParamWrapper;
import LearnMore.security.IgnoreSecurity;
import LearnMore.service.CourseService;
import LearnMore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Lee on 2017/6/8 0008.
 */
@RestController
@RequestMapping(value = "/exam")
public class ExamController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/post",method = RequestMethod.POST)
    @CrossOrigin
    @IgnoreSecurity
    public Response getExamOptionList(ExamParamWrapper wrapper){
        //拿到选项队列，然后直接和正确答案做比较，再根据正确个数/全部个数*100得到总分
        System.out.println("username："+wrapper.getUserName());
        System.out.println("长度："+wrapper.getOptionList());
        String res=courseService.checkExamAndGetPoint(wrapper);
        userService.updateCourseDetailForExam(wrapper.getUserName(),wrapper.getCourseName(),res);//更新该用户的课程成绩
        return new Response().success(res);
    }
}
