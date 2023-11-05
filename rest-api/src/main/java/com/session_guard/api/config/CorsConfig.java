package com.session_guard.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // 모든 출처에서 접근 허용
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용된 HTTP 메소드
                        .allowedHeaders("Content-Type", "Authorization", "User-Agent"); // 허용된 헤더들
            }
        };
    }
}
