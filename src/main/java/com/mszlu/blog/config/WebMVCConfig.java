package com.mszlu.blog.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ginga
 * @version 1.0
 * @ClassName WebMVCConfig
 * @Date 3/7/2022 下午11:17
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
//        跨域配置
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080");
    }
}
