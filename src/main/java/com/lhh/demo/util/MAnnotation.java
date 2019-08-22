package com.lhh.demo.util;

import java.lang.annotation.*;

//注解用在方法上
@Target(ElementType.METHOD)
//运行期保存注解
@Retention(RetentionPolicy.RUNTIME)
//注解包含在javadoc中
@Documented
//允许子类继承父类的注解
@Inherited
public @interface MAnnotation {
    public int id();
    public String dec() default "has no description";
    public String route();
}
