package LearnMore.security;

import java.lang.annotation.*;

/**
 * 忽略安全性检查的注解，使用该注解的controller方法不会被拦截
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreSecurity {
}
