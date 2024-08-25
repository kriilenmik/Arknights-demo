package com.example.spring3.config;

import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//前后端端口跨越问题解决
@Configuration
public class SpringConfig {
    private static final long MAX_AGE = 24 * 60 *60;

    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //corsConfiguration.addAllowedOrigin("http://localhost:8080");    //设置源访问地址
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");    //设置访问源请求头
        corsConfiguration.addAllowedMethod("*");    //设置源请求方法
        corsConfiguration.setMaxAge(MAX_AGE);       //设置过期时间
        source.registerCorsConfiguration("/**", corsConfiguration); //对接口配置跨域设置
        return new CorsFilter(source);
    }
}

