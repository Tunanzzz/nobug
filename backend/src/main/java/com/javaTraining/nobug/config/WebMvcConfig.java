//package com.javaTraining.nobug.config;
//
//import com.javaTraining.nobug.interceptor.UserInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @Description: springMVC配置
// * @Author: 新写的旧代码
// * @CreateTime: 2022/3/24
// */
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 用户拦截器
//        registry.addInterceptor(new UserInterceptor())
//                // 需要拦截的请求
//                .addPathPatterns("/user/**")
//                // 需要放行的请求
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/user/register")
//                // 添加swagger-ui的放行路径
//                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/doc.html/**","/user/**")
//        ;
//    }
//
//}