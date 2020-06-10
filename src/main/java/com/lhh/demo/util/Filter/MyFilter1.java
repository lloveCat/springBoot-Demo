package com.lhh.demo.util.Filter;

import com.lhh.demo.pojo.LoginInfo;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//Filter方式2
//@Order(1)
//@WebFilter(filterName = "MSecurity",urlPatterns = {"/*"})
//在DemoApplication中使用@ServletComponentScan( basepackages = "com.lai.huihui.demo.util.Filter")扫描webFilter注解
public class MyFilter1 implements Filter {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MyFilter1.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("this is my Filter1 init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("this is my Filter1 doFilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestPath = request.getRequestURI();
        HttpSession session = request.getSession();
        LoginInfo li = (LoginInfo) session.getAttribute("userLoginInfo");
        log.info(requestPath);
        if (li != null || requestPath.equals("/") || requestPath.equals("/index.html") || requestPath.equals("/user/onLogin")) {
            filterChain.doFilter(request, response);
        } else {
            log.info("not login, redirect to index.html");
            response.sendRedirect("/index.html");
        }
    }

    @Override
    public void destroy() {
        log.info("this is my Filter1 destroy");
    }
}
