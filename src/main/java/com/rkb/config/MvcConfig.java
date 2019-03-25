package com.rkb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: Aisake
 * @Date: 19-3-11 下午4:40
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
        registry.addViewController("/submit").setViewName("submit");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/").setViewName("submit");
    }
}
