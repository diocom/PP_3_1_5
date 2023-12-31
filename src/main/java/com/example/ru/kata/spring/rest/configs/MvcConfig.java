package com.example.ru.kata.spring.rest.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers (ViewControllerRegistry reg) {
        reg.addViewController("/user").setViewName("user");
        reg.addViewController("/admin").setViewName("admin");
    }
}
