package com.nowcoder.configuration;

import com.nowcoder.interceptor.LoginRequiredInterceptor;
import com.nowcoder.interceptor.PassportInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//@Component  //过时
//public class WendaWebConfiguration extends WebMvcConfigurerAdapter {
//
//    @Autowired
//    PassportInterceptor passportInterceptor;
//
//    @Autowired
//    LoginRequiredInterceptor loginRequiredInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(passportInterceptor);
//        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/user/*");
//        super.addInterceptors(registry);
//    }
//}
@Component  //spring5.0以后要实现WebMvcConfigurer接口
public class WendaWebConfiguration implements WebMvcConfigurer{

    @Autowired
    PassportInterceptor passportInterceptor;

    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    @Override
    //loginRequiredInterceptor要加在passportInterceptor拦截器之后
    //因为loginRequiredInterceptor拦截器用了hostHoder
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        //当访问/user/*这些页面的时候才有这个拦截器
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/user/*");
    }


//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//    }
}
