package LearnMore.security;
import LearnMore.security.web.WebContext;
import LearnMore.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 基于 Spring AOP 写一个切面类，用于拦截 Controller 类的方法，并从请求头中获取 token，最后对 token 有效性进行判断
 * 对于加了@IgnoreSecurity注解的controller方法，会直接调用目标方法
 * Created by Lee on 2017/5/7 0007.
 */
@Aspect
@Component
public class SecurityAspect {

    private static final String DEFAULT_TOKEN_NAME = "X-Token";
    private static final String DEFAULT_PERMISSION_NAME = "X-Permission";

    private TokenManager tokenManager;
    private String tokenName=DEFAULT_TOKEN_NAME;
    private String permissionName=DEFAULT_PERMISSION_NAME;


    @Autowired
    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }



    //声明切点（作用：拦截在方法上标注了@RequestMapping注解的请求）
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void annotationPointCut(){

    }

    @Around("annotationPointCut()")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        // 从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        // 若目标方法忽略了安全性检查，则直接调用目标方法（使用@IgnoreSecurity注解）
        if (method.isAnnotationPresent(IgnoreSecurity.class)) {
            return pjp.proceed();
        }
        // 从 request header 中获取当前 token
        String token = WebContext.getRequest().getHeader(tokenName);
        // 检查 token 有效性
        if (!tokenManager.checkToken(token)) {
            String message = String.format("token [%s] is invalid", token);
            throw new TokenException(message);
        }
        //有问题，如果一个要管理员权限的资源给一个header没有permission的访问，他自然会是为null，然后跳过验证，这样写法只能拦截header中的permission不为空的
//        String permission = WebContext.getRequest().getHeader(permissionName);
//        // 检查 permission 有效性
//        if (StringUtil.isNotEmpty(permission)){//对于header中包含X-Permission的就进行permission的验证，因为普通用户在登录成功后返回的data里面是不包含permission字段的
//            if (!tokenManager.checkPermission(permission)) {
//                String message = String.format("permission [%s] is invalid", permission);
//                throw new TokenException(message);
//            }
//        }

        // 调用目标方法
        return pjp.proceed();
    }

    //todo 自定义注解用来验证permission（有加这个注解的就要验证）
    @Pointcut("@annotation(LearnMore.security.CheckPermission)")
    public void checkPermissionAnnotationPointCut(){

    }

    /**
     * 加入@CheckPermission注解的会判断permission
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("checkPermissionAnnotationPointCut()")
    public Object executeCheckPermission(ProceedingJoinPoint pjp) throws Throwable {
        // 从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();

        String permission = WebContext.getRequest().getHeader(permissionName);
        // 检查 permission 有效性
        //对于header中包含X-Permission的就进行permission的验证，因为普通用户在登录成功后返回的data里面是不包含permission字段的
        if (!tokenManager.checkPermission(permission)) {
            String message = String.format("permission [%s] is invalid", permission);
            throw new TokenException(message);
        }
        System.out.println("CheckPermission is done");
        // 调用目标方法
        return pjp.proceed();
    }
}