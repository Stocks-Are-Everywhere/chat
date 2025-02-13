package com.one.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 엔드포인트에 CORS 적용
                //.allowedOrigins("*") // 모든 도메인 허용
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowCredentials(true) // 인증 정보 포함 허용
                .allowedHeaders("*") // 모든 헤더 허용
                .maxAge(3600); // 캐시 유지 시간 (초)

        registry.addMapping("/api/session-id")
                .allowedOriginPatterns("*")
                .allowCredentials(true);
    }
}
