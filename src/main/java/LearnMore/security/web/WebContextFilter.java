package LearnMore.security.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于管理 WebContext 对象的生命周期
 */
@WebFilter(urlPatterns = "/*")
public class WebContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return;
        }

        WebContext.init(request, response);
        try {
            filterChain.doFilter(request, response);
        } finally {
            WebContext.destroy();
        }
    }

    @Override
    public void destroy() {
    }
}
