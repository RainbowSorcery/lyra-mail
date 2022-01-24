package com.lyra.mail.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.lyra.mail.ware.mapper")
@EnableFeignClients(basePackages = "com.lyra.mail.ware.feign")
public class LyraMailWareApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyraMailWareApplication.class, args);
    }
}
