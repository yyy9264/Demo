package com.itheima.config;

import com.itheima.interceptor.DemoInterceptor;
import com.itheima.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 *注册拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //自定义的拦截器对象
//    @Autowired
//    private DemoInterceptor demoInterceptor;
//    @Autowired
//    private TokenInterceptor tokenInterceptor;
//    @Override
//    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
//        //注册自定义的拦截器
//        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**")//拦截所有请求
//                .excludePathPatterns("/login");//放行登录请求
//    }
}
