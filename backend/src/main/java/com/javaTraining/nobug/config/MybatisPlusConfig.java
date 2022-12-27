package com.javaTraining.nobug.config;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MybatisPlusConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        logger.info("====Mybatis Plus====");
        return new PaginationInterceptor();
    }


}

