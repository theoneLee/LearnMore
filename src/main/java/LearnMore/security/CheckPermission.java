package LearnMore.security;

import java.lang.annotation.*;

/**
 * 加入这个注解时就要检查permission
 * Created by Lee on 2017/6/5 0005.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermission {

}
