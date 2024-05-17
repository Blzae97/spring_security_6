package com.spring.security.csrf.form.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/form", "/csrf", "/formCsrf").permitAll()
                        .anyRequest().denyAll()
                );

        http
                .formLogin(Customizer.withDefaults());

        http
                .csrf(Customizer.withDefaults());

        return http.build();
    }
}
