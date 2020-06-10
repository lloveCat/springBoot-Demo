package com.lhh.demo.MySpringMVCDemo;

/**
 * Created by lai.huihui on 2020/6/10.
 */
@MyScanner(path = "com.lhh.demo.MySpringMVCDemo")
public class MainClass {
    public static void main(String args[]) throws Exception{
        MyDispatherServlet myDispatherServlet = new MyDispatherServlet();
        myDispatherServlet.init(MainClass.class);
        System.out.println(111);
        myDispatherServlet.invokeFunc("http://localhost:8080/myController/test", new Object[]{"laihuihui"});
    }
}
