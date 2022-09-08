package com.mszlu.blog.config;

import com.mszlu.blog.handler.LoginInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ginga
 * @version 1.0
 * @ClassName WebMVCConfig
 * @Date 3/7/2022 下午11:17
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
//        跨域配置
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080");
    }

    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor) // 添加拦截器
                .addPathPatterns("/test")
                .addPathPatterns("/comments/create/change");
    }
}
