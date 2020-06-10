package com.lhh.demo.mybatisDemo;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by lai.huihui on 2020/6/10.
 */
public class MybatisInvocation implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理开始,方法名:" + method.getName());
        MyInsertAnno insertAnno = null;
        MyUpdateAnno updateAnno = null;
        MyDelAnno delAnno = null;
        MyQueryAnno queryAnno = null;
        Annotation[] annos = method.getAnnotations();
        if ((insertAnno = method.getAnnotation(MyInsertAnno.class)) != null) {
            System.out.println("this is insert method: " + insertAnno.value());
        } else if ((updateAnno = method.getAnnotation(MyUpdateAnno.class)) != null) {
            System.out.println("this is update method: " + updateAnno.value());
        } else if ((delAnno = method.getAnnotation(MyDelAnno.class)) != null) {
            System.out.println("this is delete method: " + delAnno.value());
        } else if ((queryAnno = method.getAnnotation(MyQueryAnno.class)) != null) {
            System.out.println("this is query method: " + queryAnno.value());
        }
        return 1;
    }
}
