package com.javaTraining.nobug.config;

import com.javaTraining.nobug.interceptor.TokenInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * 跨域请求支持/token拦截
 * tip:只能写在一个配置类里
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private TokenInterceptor tokenInterceptor;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //构造方法
    public WebConfiguration(TokenInterceptor tokenInterceptor){
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .maxAge(3600)
                .allowedMethods("GET","POST","DELETE","PUT")
                .allowedOrigins("*");
        logger.info("====解决跨域问题成功！！！====");

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer){
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // 解决swagger无法访问
//        registry.addResourceHandler("/swagger-ui.html")
//                .addResourceLocations("classpath:/resources/");
//        // 解决swagger的js文件无法访问
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/resources/webjars/");
//        // 解决静态资源无法访问
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/resources/static");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        // 排除拦截
        excludePath.add("/user/login");  //登录和注册
        excludePath.add("/user/register");  //登录和注册
        excludePath.add("/user/index");  //登录和注册
        excludePath.add("/webjars/**");
        excludePath.add("/favicon.ico");
        excludePath.add("/static/**");  //静态资源
        excludePath.add("/assets/**");  //静态资源
        excludePath.add("/swagger-ui.html/**");  //swagger
        excludePath.add("/v2/**");  //swagger
        excludePath.add("/srs/v1/**");  //swagger
        excludePath.add("/doc.html/**");  //doc.html
        excludePath.add("/doc-resources/**");  //swagger
        excludePath.add("/swagger-resources/**");  //swagger
        logger.info("====通过登录拦截器====");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

