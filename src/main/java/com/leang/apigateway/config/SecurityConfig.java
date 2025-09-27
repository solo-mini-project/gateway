package com.leang.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        // Allow access to swagger and api-docs paths
                        .pathMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**",
                                "/product-service/v3/api-docs/**", "/auth-service/v3/api-docs/**",
                                "/category-service/v3/api-docs/**").permitAll()
                        .anyExchange().authenticated() // All other requests require authentication
                )
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec.jwt(Customizer.withDefaults()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}
