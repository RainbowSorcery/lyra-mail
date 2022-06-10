package com.lyra.mail.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = {"com.lyra.mail.auth.mapper"})
@EnableFeignClients(basePackages = {"com.lyra.mail.auth.feign"})
public class LyraMailAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyraMailAuthApplication.class);
    }
}
