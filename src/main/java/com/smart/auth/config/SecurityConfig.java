package com.smart.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // =========================
            // CSRF (Disabled for REST APIs)
            // =========================
            .csrf(csrf -> csrf.disable())

            // =========================
            // Authorization Rules
            // =========================
            .authorizeHttpRequests(auth -> auth

                // Public APIs
                .requestMatchers(
                    "/api/auth/**",
                    "/api/test/public"
                ).permitAll()

                // Swagger / OpenAPI
                .requestMatchers(
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**",
                    "/v3/api-docs.yaml",
                    "/swagger-resources/**",
                    "/webjars/**"
                ).permitAll()

                // Admin APIs
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                // All other APIs
                .anyRequest().authenticated()
            )

            // =========================
            // Authentication Mechanism
            // =========================
            .httpBasic(Customizer.withDefaults())

            // =========================
            // User Details Source
            // =========================
            .userDetailsService(userDetailsService);

        return http.build();
    }

    // =========================
    // Password Encoder
    // =========================
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // =========================
    // Authentication Manager
    // =========================
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
