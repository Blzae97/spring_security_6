package com.spring.security.login.infrastructure.config;

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
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()); // 모든 요청에 대해서는 권한을 확인한다.

        http
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true) // 로그인을 성공했을 때 이동할 사이트 경로(핸들러가 설정되어 있다면 우선 순위가 낮음)
                        .failureUrl("/failed") // 로그인에 실패 했을 경우 이동할 사이트 경로(핸들러가 설정되어 있다면 우선 순위가 낮음)
                        .usernameParameter("user_id") // form에 들어가는 id가 username인 input의 name 값
                        .passwordParameter("pass_wd") // from에 들어가는 id가 paassword의 input의 name 값
                        .successHandler((request, response, authentication) -> { // 로그인에 성공 했을 떄 (우선 순위가 높음)
                            System.out.println("authentication: " + authentication);
                            response.sendRedirect("/home");
                        })
                        .failureHandler((request, response, exception) -> { // 로그인에 실패 했을 때 (우선 순위가 높음)
                            System.out.println("exception:" + exception);
                            response.sendRedirect("/login");
                        })
                        .permitAll() // loginPage(). loginProcessingUrl(), failureUrl()에 대해서 모든 사용자의 접근 허용
                );

        return http.build();
    }
}
