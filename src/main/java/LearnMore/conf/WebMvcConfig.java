package LearnMore.conf;

import LearnMore.security.web.WebContextFilter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 即保留spring boot的自动配置，还要增加自己额外的配置时，创建这个类
 * 注意不能有@EnableWebMvc，否则就是完全自己控制MVC配置而失去了spring boot提供的默认配置
 * Created by Lee on 2017/5/17 0017.
 */
@Configuration
@ServletComponentScan(basePackageClasses = {WebContextFilter.class})//扫描filter，servlet，listener组件，会将其自动注册（使其可用）
public class WebMvcConfig extends WebMvcConfigurerAdapter{


}
