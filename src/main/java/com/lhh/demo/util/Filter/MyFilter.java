package com.lhh.demo.util.Filter;

import com.lhh.demo.controller.UserInfoController;
import com.lhh.demo.pojo.LoginInfo;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@Component
public class MyFilter implements Filter {       //Filter类

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("this is myFilter Init function()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("this is myFilter doFilter function()");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestPath = request.getRequestURI();
        HttpSession session = request.getSession();
        LoginInfo li = (LoginInfo) session.getAttribute("userLoginInfo");
        log.info("requestPath: " + requestPath);
        if (li == null && !requestPath.equals("/" ) && !requestPath.equals("/index.html")) {
            log.info("not login, redirect to index.html");
            response.sendRedirect("classpath: static/index.html");
        } else if (li != null || requestPath.equals("/index.html") || requestPath.equals("/")) {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        log.info("this is myFilter destroy function()");
    }
}
