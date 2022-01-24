package com.lyra.mail.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.lyra.mail.admin.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class LyraMailApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyraMailApplication.class, args);
    }
}
