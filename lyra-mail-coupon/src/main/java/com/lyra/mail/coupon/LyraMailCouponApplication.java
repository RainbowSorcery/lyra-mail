package com.lyra.mail.coupon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan(basePackages = "com.lyra.mail.coupon.mapper")
@EnableDiscoveryClient
public class LyraMailCouponApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyraMailCouponApplication.class, args);
    }
}
