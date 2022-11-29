package com.example.jingdong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许跨域请求
        corsConfiguration.setAllowCredentials(true);
        //允许任何域名使用
        corsConfiguration.addAllowedOriginPattern("*");
        //允许任何请求头
        corsConfiguration.addAllowedHeader("*");
        //允许任何请求方法（post、get等）
        corsConfiguration.addAllowedMethod("*");

        return  corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new  UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());
        return new CorsFilter(source);
    }
}