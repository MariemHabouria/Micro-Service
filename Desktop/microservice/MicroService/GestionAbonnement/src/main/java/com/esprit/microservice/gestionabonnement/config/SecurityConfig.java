package com.esprit.microservice.gestionabonnement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF (required for H2)
                .headers(headers -> headers.frameOptions().disable()) // Allow H2 console to render in a frame
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2/**").permitAll() // Allow access to H2 console
                        .anyRequest().permitAll() // Allow all other endpoints
                );

        return http.build();
    }
}
