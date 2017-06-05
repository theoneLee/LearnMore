package LearnMore.controller;

import LearnMore.entity.CommonUser;
import LearnMore.entity.Response;
import LearnMore.security.IgnoreSecurity;
import LearnMore.security.TokenManager;
import LearnMore.security.web.WebContext;
import LearnMore.service.UserService;
import LearnMore.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
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

    /**
     * 登录，成功时加入token（是管理员还需要加入permission）
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @IgnoreSecurity
    public Response login(@RequestParam("username")String username,@RequestParam("password")String password, HttpServletResponse httpServletResponse){
        CommonUser user=new CommonUser();
        user.setUsername(username);
        user.setPassword(password);
        CommonUser checkedUser=userService.checkUserPassword(user);
        if (checkedUser!=null){
            String token=tokenManager.createToken(checkedUser.getUsername());//加入token到cookie
            Cookie tokenCookie=new Cookie(DEFAULT_TOKEN_NAME,token);
            tokenCookie.setMaxAge(-1);//关闭浏览器即清除Cookie
            httpServletResponse.addCookie(tokenCookie);

            String permission=checkedUser.getPermission();//加入permission到cookie
            if (StringUtil.isNotEmpty(permission)){
                Cookie permissionCookie=new Cookie(DEFAULT_PERMISSION_NAME,permission);
                permissionCookie.setMaxAge(-1);
                httpServletResponse.addCookie(permissionCookie);//permission写入cookie
            }
            checkedUser.setPermission(null);
            return new Response().success(checkedUser);//todo 注意：之后客户端每次请求都将cookie中的token作为请求头，发送到服务端
        }
        return new Response().failure("login_failure");
    }



}
