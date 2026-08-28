package com.cafe.cafeBackend.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http

                // JWT API → disable CSRF
                .csrf(csrf -> csrf.disable())

                // JWT is stateless
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/api/auth/**"
                        ).permitAll()

                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs",
                                "/v3/api-docs/**"
                        ).permitAll()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/menu/**"
                        ).permitAll()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/menu/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/menu/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/menu/**"
                        ).hasRole("ADMIN")

                        // CUSTOMER + ADMIN can create orders
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/orders/**"
                        ).hasAnyRole(
                                "CUSTOMER",
                                "ADMIN"
                        )

                        // Only ADMIN can update orders
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/orders/**"
                        ).hasRole("ADMIN")

                        // Only ADMIN can delete orders
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/orders/**"
                        ).hasRole("ADMIN")
                        // CUSTOMER + ADMIN can create reservations
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/reservations/**"
                        ).hasAnyRole(
                                "CUSTOMER",
                                "ADMIN"
                        )

                        // Only ADMIN can update reservations
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/reservations/**"
                        ).hasRole("ADMIN")

                        // Only ADMIN can delete reservations
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/reservations/**"
                        ).hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                // JWT filter
                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}