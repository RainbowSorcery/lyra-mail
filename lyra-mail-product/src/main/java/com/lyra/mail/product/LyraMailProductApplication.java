package com.lyra.mail.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.lyra.mail.product.mapper")
@ComponentScan(basePackages = "com.lyra")
@EnableFeignClients(basePackages = {"com.lyra.mail.product.feign"})
@EnableCaching
public class LyraMailProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyraMailProductApplication.class, args);
    }
}
