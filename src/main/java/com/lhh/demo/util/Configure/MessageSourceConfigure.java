package com.lhh.demo.util.Configure;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfigure {   //配置类
//    @Bean(name = "MyMessageSource")
//    public MessageSource getMessageSource() {       //配置一个MessageSource Bean实例.
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasename("i18n/message");  //路径
//        messageSource.setDefaultEncoding("UTF-8");  //默认编码
//        return messageSource;
//    }
}
