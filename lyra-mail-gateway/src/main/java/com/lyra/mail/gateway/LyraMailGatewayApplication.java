package com.lyra.mail.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LyraMailGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyraMailGatewayApplication.class, args);
    }
}
