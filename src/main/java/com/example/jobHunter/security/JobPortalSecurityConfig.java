package com.example.jobHunter.security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class JobPortalSecurityConfig {

    @Qualifier("publicPaths")
    private final List<String> publicPaths;

    @Qualifier("securedPaths")
    private final List<String> securedPaths;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {

        return http.csrf(csrfConfig -> csrfConfig.disable())
                .authorizeHttpRequests(requests -> {
                    publicPaths.forEach(path-> requests.requestMatchers(path).permitAll());
                    securedPaths.forEach(path-> requests.requestMatchers(path).authenticated());
                })
                .httpBasic(withDefaults())
                .build();
//        return  http.authorizeHttpRequests((
//requests) -> requests.anyRequest().authenticated())
//                .formLogin(flc -> flc.disable())
//                .httpBasic(withDefaults())
//                .build();


    }
}


