package com.spring.security.session.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/invalid-session", "/expired").permitAll()
                        .anyRequest().authenticated()
                );

        http
                .formLogin(Customizer.withDefaults());

        http
                .sessionManagement(session -> session
                        // 이미 만료 된 세션으로 요청하는 사용자를 특정 엔드포인트로 리다이렉션 URL 설정
                        .invalidSessionUrl("/invalid-session")
                        // 사용자당 세션 최대 개수
                        .maximumSessions(1)
                        // true -> 최대 세션수에 도달 했을 때 사용자의 인증을 방지
                        // false -> 인증하는 사용자에게 접근을 허용하 기존의 세션은 만료한다.
                        .maxSessionsPreventsLogin(false)
                        // 세션을 만료하고 나서 리다이렉션 할 URL 설정
                        .expiredUrl("/expired")
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.withUsername("user").password("{noop}1111").roles("USER").build();
        return new InMemoryUserDetailsManager(userDetails);
    }
}
