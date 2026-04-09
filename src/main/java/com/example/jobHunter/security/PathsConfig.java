package com.example.jobHunter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration class PathsConfig {
    @Bean(name = "publicPaths")
    List<String>Paths() {
        return List.of(
                "/api/contacts",
                "/api/auth/login",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/api/v3/api-docs/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**",
                "/v3/api-docs",
                "/auth/login/public",
                "/auth/register/public",
                "/todos/**"
                "/auth/register/public",
                "/swagger-ui/index.html",
                "/actuator/**"
        );
    }

    @Bean(name = "securedPaths")
    List<String> securedPaths() {
        return List.of(
                "/api/companies",
                "/api/**"
        );
    }
}
