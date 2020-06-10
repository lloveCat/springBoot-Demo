package com.lhh.demo.MySpringMVCDemo;

/**
 * Created by lai.huihui on 2020/6/10.
 */
@MyRequestMapping("/myController")
@MyCon("myController")
public class MyController {
    @MyQualifer("myServiceImpl")
    private MyService myService;

    @MyRequestMapping("/test")
    public void test(String name, int id) {
        System.out.println("invoke test and args: " + name);
        myService.test();
    }

    @MyRequestMapping("/secondServlet")
    public Object secondServlet(String message) {
        return message.replaceAll(" ","");
    }
}
