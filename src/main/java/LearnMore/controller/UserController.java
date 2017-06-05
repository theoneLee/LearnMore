package LearnMore.controller;

import LearnMore.entity.CommonUser;
import LearnMore.entity.Response;
import LearnMore.security.IgnoreSecurity;
import LearnMore.security.TokenManager;
import LearnMore.security.web.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Lee on 2017/6/5 0005.
 */
@RestController
@RequestMapping("/user")
public class UserController {//todo 登录，注销，修改密码，注册
    private static final String DEFAULT_TOKEN_NAME = "X-Token";
    private static final String DEFAULT_PERMISSION_NAME = "X-Permission";

    @Autowired
    private UserService userService;

    @Autowired
    private TokenManager tokenManager;

    @RequestMapping(value = "/signin" ,method = RequestMethod.POST)
    @IgnoreSecurity
    public Response signin(@RequestBody CommonUser user){
        userService.signIn(user);
        return new Response().success();
    }

    /**
     * 在header拿到该token，然后移除该token
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public Response logout() {
        String token = WebContext.getRequest().getHeader(DEFAULT_TOKEN_NAME);
        tokenManager.removeToken(token);
        return new Response().success();
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @IgnoreSecurity
    public Response login(@RequestBody CommonUser user, HttpServletResponse httpServletResponse){
        CommonUser checkedUser=userService.checkUserPassword(user);

    }

}
