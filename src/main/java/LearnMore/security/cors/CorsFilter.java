package LearnMore.security.cors;

import LearnMore.util.CollectionUtil;
import LearnMore.util.StringUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 过滤所有的 HTTP 请求，并将 CORS 响应头写入 response 对象中
 * todo 设置allowOrigin为前端所属的origin即可解决cors问题
 * Created by Lee on 2017/5/7 0007.
 */
//@WebFilter(
//        urlPatterns = "/*",
//        initParams = {
//                @WebInitParam(name = "allowOrigin",value = "*"),//todo 当配置为*时，下面allowCredentials又是true时（允许请求携带cookie），该请求会失效 （但是自己测试时好像没这个问题） 参考http://netsecurity.51cto.com/art/201311/419179.htm
//                @WebInitParam(name = "allowMethods",value = "GET,POST,PUT,DELETE,OPTIONS"),
//                @WebInitParam(name = "allowCredentials",value = "true"),
//                @WebInitParam(name = "allowHeaders",value = "Content-Type,X-Token,X-Permission")
//        }
//)
public class CorsFilter implements Filter {

    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    private String exposeHeaders;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //从 web.xml 中读取相关 Filter 初始化参数，并将在处理 HTTP 请求时将这些参数写入对应的 CORS 响应头中
        allowOrigin = filterConfig.getInitParameter("allowOrigin");
        allowMethods = filterConfig.getInitParameter("allowMethods");
        allowCredentials = filterConfig.getInitParameter("allowCredentials");
        allowHeaders = filterConfig.getInitParameter("allowHeaders");
        exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //CORS 规范中定义 Access-Control-Allow-Origin 只允许两种取值，要么为 *，要么为具体的域名，也就是说，不支持同时配置多个域名。为了解决跨多个域的问题，需要在代码中做一些处理，这里将 Filter 初始化参数作为一个域名的集合（用逗号分隔），只需从当前请求中获取 Origin 请求头，就知道是从哪个域中发出的请求，若该请求在以上允许的域名集合中，则将其放入 Access-Control-Allow-Origin 响应头
        if (StringUtil.isNotEmpty(allowOrigin)) {
            List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
            if (CollectionUtil.isNotEmpty(allowOriginList)) {
                String currentOrigin = request.getHeader("Origin");
                if (allowOriginList.contains(currentOrigin)) {
                    response.setHeader("Access-Control-Allow-Origin", currentOrigin);
                }
            }
        }
        if (StringUtil.isNotEmpty(allowMethods)) {
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (StringUtil.isNotEmpty(allowCredentials)) {
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (StringUtil.isNotEmpty(allowHeaders)) {
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }
        if (StringUtil.isNotEmpty(exposeHeaders)) {
            response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
    }
}