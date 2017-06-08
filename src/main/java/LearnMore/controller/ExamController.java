package LearnMore.controller;

import LearnMore.entity.Response;
import LearnMore.entity.wrapper.ExamParamWrapper;
import LearnMore.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Lee on 2017/6/8 0008.
 */
@RestController
@RequestMapping(value = "/exam")
public class ExamController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public Response getExamOptionList(@RequestBody ExamParamWrapper wrapper){
        //拿到选项队列，然后直接和正确答案做比较，再根据正确个数/全部个数*100得到总分
        String res=courseService.checkExamAndGetPoint(wrapper);
        return new Response().success(res);
    }
}
