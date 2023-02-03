package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AppApplication {
    private static ApplicationContext applicationContext;


    public static void main(String[] args) {
        applicationContext = SpringApplication.run(AppApplication.class, args);
    }
}
