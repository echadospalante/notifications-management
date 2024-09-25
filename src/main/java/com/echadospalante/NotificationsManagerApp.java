package com.echadospalante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class NotificationsManagerApp {

    public static void main(String[] args) {
        SpringApplication.run(NotificationsManagerApp.class, args);
    }

}
