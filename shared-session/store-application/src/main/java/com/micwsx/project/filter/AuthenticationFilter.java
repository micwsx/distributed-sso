package com.micwsx.project.filter;

import model.MyHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Statement;

/**
 * @author Michael
 * @create 10/30/2020 1:42 PM
 * 添加@Component注解注册Store应用程序认证过滤器
 */
@Component
public class AuthenticationFilter implements Filter {

    public final static String USER = "user";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        MyHttpServletRequest myHttpServletRequest = new MyHttpServletRequest(httpServletRequest, redisTemplate);

        Object userInfo = myHttpServletRequest.getSession().getAttribute(USER);
        // "/store/home"
        String servletPath = myHttpServletRequest.getServletPath();
        String returnUrl = myHttpServletRequest.getParameter("returnUrl");
        if (StringUtils.isEmpty(returnUrl))
            returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + servletPath;
        // 若请求不是login相关资源且未登录,则重定向到登录页面
        if (!servletPath.startsWith("/login") && userInfo == null) {
            ((HttpServletResponse) response).sendRedirect("/login/index?returnUrl=" + returnUrl);
//            request.getRequestDispatcher("/login/index?returnUrl=" + returnUrl).forward(myHttpServletRequest, response);
        }

        System.out.println("开始放行");
        chain.doFilter(myHttpServletRequest, httpServletResponse);
        System.out.println("结束放行");

    }

    @Override
    public void destroy() {

    }
}
