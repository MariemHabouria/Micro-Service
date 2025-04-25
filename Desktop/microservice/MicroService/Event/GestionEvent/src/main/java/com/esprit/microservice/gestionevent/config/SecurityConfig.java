package com.esprit.microservice.gestionevent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // ✅ Custom JWT converter to read roles from Keycloak's "realm_access.roles"
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_"); // Required for hasRole()
        grantedAuthoritiesConverter.setAuthoritiesClaimName("realm_access.roles");

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtConverter;
    }

    // ✅ Main security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for API
                .authorizeHttpRequests(auth -> auth
                        // Allow access to H2 console
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()

                        // Protect admin endpoints (requires ROLE_admin)
                        .requestMatchers(new AntPathRequestMatcher("/events/admin/**")).hasRole("admin")

                        // Protect user endpoints (requires ROLE_user)
                        .requestMatchers(new AntPathRequestMatcher("/events/user/**")).hasRole("user")

                        // Any other request must be authenticated
                        .anyRequest().authenticated()
                )

                // Allow H2 console to be rendered in browser frames
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))

                // Enable JWT token validation
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                )
                .build();
    }
}
