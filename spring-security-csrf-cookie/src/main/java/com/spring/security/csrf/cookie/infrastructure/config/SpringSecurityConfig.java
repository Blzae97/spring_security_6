package com.spring.security.csrf.cookie.infrastructure.config;

import com.spring.security.csrf.cookie.application.handler.SpaCsrfTokenRequestHandler;
import com.spring.security.csrf.cookie.presentation.filter.CsrfCookieFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/cookie", "/csrf", "/cookieCsrf").permitAll()
                        .anyRequest().denyAll()
                );

        http
                .formLogin(Customizer.withDefaults());

        http
                .csrf(cf -> cf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())
                );

        http
                .addFilterBefore(new CsrfCookieFilter(), BasicAuthenticationFilter.class);

        return http.build();
    }
}
