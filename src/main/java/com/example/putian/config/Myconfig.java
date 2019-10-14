package com.example.putian.config;

import com.example.putian.interceptor.GlobalInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
@Configuration
public class Myconfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor handlerInterceptor=new GlobalInterceptor();
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(handlerInterceptor);
        interceptorRegistration.addPathPatterns("/**");
        List<String > pattens=new ArrayList<>();
        pattens.add("/bootstrap3/**");
        pattens.add("/css/**");
        pattens.add("/images/**");
        pattens.add("/js/**");
        pattens.add("/user/reg");
        pattens.add("/user/login");
        pattens.add("/user/changePassword");
        pattens.add("/user/change_info");
       pattens.add("/web/**");
        pattens.add("/web/userdata.html");
        pattens.add("/web/password.html");


        interceptorRegistration.excludePathPatterns(pattens);


    }
}
