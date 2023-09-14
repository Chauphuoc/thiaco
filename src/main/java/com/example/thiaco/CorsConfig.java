package com.example.thiaco;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Cho phép tất cả các nguồn gốc (bạn có thể điều chỉnh để chỉ chấp nhận tên miền cụ thể)
        config.addAllowedOrigin("*");

        // Cho phép các phương thức HTTP (GET, POST, PUT, DELETE, v.v.)
        config.addAllowedMethod("*");

        // Cho phép các tiêu đề yêu cầu (ví dụ: Authorization, Content-Type)
        config.addAllowedHeader("*");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}