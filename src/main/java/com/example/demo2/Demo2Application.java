package com.example.demo2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@MapperScan("com.example.demo2.mapper")
@EnableWebSecurity
@EnableScheduling //定时任务
@EnableCaching
@EnableTransactionManagement // 开启事务，保证redis与mysql中数据的一致性
public class Demo2Application  {

    public static void main(String[] args) {

        SpringApplication.run(Demo2Application.class, args);
    }


}
