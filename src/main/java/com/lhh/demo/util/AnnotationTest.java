package com.lhh.demo.util;

import ch.qos.logback.classic.pattern.SyslogStartConverter;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

//注解测试,使用自定义注解模拟SprintMVC 路由请求调用函数
public class AnnotationTest {
    public static final int INVOKE_ERROR = 0;
    public static final int INVOKE_SUCCESS = 1;

    public AnnotationTest() {
        System.out.println("constructor is invoked");
    }
    @MAnnotation(id = 1,dec = "sayHello description",route = "sayHelloAction")
    public void sayHello(int id, String dec){
        System.out.println(dec + " " + id);
    }

    @MAnnotation(id = 2,dec = "sayWorld description",route = "sayWorldAction")
    public void sayWorld(int userId, String name){
        System.out.println("sayWorld is invoke, userId = " + userId +", userName = " + name);
    }

    @MAnnotation(id = 3,route = "sayDefaultAction")    //id是必须的，dec有default，非必须
    public void sayDefault(int userId){
        System.out.println("sayDefault is invoke, userId = " + userId);
    }

    public static void main(String[] args) {
        try{
            request("sayHelloAction");
            Thread.sleep(2000);
            request("sayWorldAction");
            Thread.sleep(2000);
            request("sayDefaultAction");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void request(String route) throws Exception{
        System.out.println("param route is " + route +", prepare to match it to invoke methed");
        int result = match(route);
        System.out.println(result == 0? "invoke error, the route " + route + " is not find" : " invoke success");
    }
    public static int match(String route) throws Exception{
        int result = INVOKE_ERROR;
        Class c = AnnotationTest.class;
        Method[] methods = AnnotationTest.class.getDeclaredMethods();
        if (!StringUtils.isEmpty(route)) {
            for (Method m : methods) {
                boolean hasAnnotation = m.isAnnotationPresent(MAnnotation.class);
                if (hasAnnotation) {
                    MAnnotation ma = m.getAnnotation(MAnnotation.class);
                    if (route.equals(ma.route())) {
                        result = INVOKE_SUCCESS;
                        AnnotationTest annotationTest =(AnnotationTest) c.newInstance();
                        switch (route) {    //jdk1.8支持 switch string类型
                            case "sayHelloAction" :
                                m.invoke(annotationTest, ma.id(),ma.dec()); //使用注解成员传参
                                break;
                            case "sayWorldAction" :
                                m.invoke(annotationTest, 1, "laihuihui");
                                break;
                            case "sayDefaultAction" :
                                m.invoke(annotationTest, 2);
                                break;
                            default:
                                result = INVOKE_ERROR;
                                break;
                        }
                    }
                }
            }
        }
        return result;
    }
}
