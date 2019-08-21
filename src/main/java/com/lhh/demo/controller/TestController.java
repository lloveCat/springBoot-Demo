package com.lhh.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("test")
@RestController
public class TestController {

    @RequestMapping("getSessionId")
    @ResponseBody
    public String getSessionid(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("haha") == null) {
            System.out.println("空，添加");
            session.setAttribute("haha","hello world");
        }
        return request.getSession().getId();
    }
}
