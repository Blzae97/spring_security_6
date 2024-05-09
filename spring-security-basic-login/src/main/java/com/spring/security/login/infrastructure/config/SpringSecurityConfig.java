package com.spring.security.login.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()); // 모든 요청에 대해서 권한 체크

        http
                .httpBasic(basic -> basic
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // http basic 인증이 실패했을 때에 대한 처리 방식 커스텀
                );

        return http.build();
    }
}
