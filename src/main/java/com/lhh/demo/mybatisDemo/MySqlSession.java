package com.lhh.demo.mybatisDemo;

import java.lang.reflect.Proxy;

/**
 * Created by lai.huihui on 2020/6/10.
 */
public class MySqlSession {
    public static <T> T getMapper(Class clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new MybatisInvocation());
    }
}
