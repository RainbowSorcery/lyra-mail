package com.lyra.mail.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LyraMailApplication {
    public static void main(String[] args) {
        SpringApplication.run(LyraMailApplication.class, args);
    }
}
