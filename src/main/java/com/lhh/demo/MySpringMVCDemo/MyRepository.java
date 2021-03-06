package com.lhh.demo.MySpringMVCDemo;

import java.lang.annotation.*;

/**
 * Created by lai.huihui on 2020/6/10.
 */
@Target(ElementType.TYPE)
//运行期保存注解
@Retention(RetentionPolicy.RUNTIME)
//注解包含在javadoc中
@Documented
//允许子类继承父类的注解
@Inherited
public @interface MyRepository {
    public String value() default "";
}

