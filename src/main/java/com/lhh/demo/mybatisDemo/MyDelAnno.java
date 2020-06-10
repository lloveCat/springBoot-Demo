package com.lhh.demo.mybatisDemo;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * Created by lai.huihui on 2020/6/10.
 */
//注解用在方法上
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@MybatisBaseAnno
public @interface MyDelAnno {
    @AliasFor(annotation = MybatisBaseAnno.class)
    public String value() default "";
}
