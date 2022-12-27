package com.javaTraining.nobug;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan("com.javaTraining.nobug.mapper")
@Slf4j
public class NobugApplication {

    public static void main(String[] args) {
        SpringApplication.run(NobugApplication.class, args);
    }

}
