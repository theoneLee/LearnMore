package LearnMore.controller;


import LearnMore.entity.Response;
import LearnMore.security.IgnoreSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lee on 2017/6/3 0003.
 */
@RestController
public class TestController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @IgnoreSecurity
    public Response test(){
        String message="test";
        return new Response().success(message);
    }


}
