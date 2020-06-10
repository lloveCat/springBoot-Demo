package com.lhh.demo.controller;


import com.lhh.demo.pojo.UserInfo;
import com.lhh.demo.service.UserInfoService;
import com.lhh.demo.util.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RequestMapping("test")
@RestController
public class TestController {
    @Autowired
    private UserInfoService userInfoService;

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
    @RequestMapping("getVueTestProductList")
    @ResponseBody
    public List<UserInfo> getVueTestProductList() {
        List<UserInfo> productList = new ArrayList<>();
//        productList.add("ServerProduct1");
//        productList.add("ServerProduct2");
//        productList.add("ServerProduct3");
//        productList.add("ServerProduct4");
        productList.add(new UserInfo("laihuihui","china","jiangxi","ganzhou"));
        productList.add(new UserInfo("huangdi","china","hubei","wuhan"));
        productList.add(new UserInfo("zhengjunlong","china","jiangxi","jian"));
        productList.add(new UserInfo("gengjia","china","shandong","liaocheng"));
        return productList;
    }

    @GetMapping("testRedis")
    public Object testRedis() {
        System.out.println("空，添加");
        UserInfo userInfo = new UserInfo("lhh","赣州","江西","宁都");
        userInfo.setOpenId("IamOpenId");
        try {
            userInfoService.addUserInfo(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultInfo.success();
    }
}
