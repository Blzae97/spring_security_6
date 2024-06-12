package com.spring.security.authentication.event.publisher.infrastructure.security.config;

import com.spring.security.authentication.event.publisher.infrastructure.event.custom.CustomAuthenticationFailureEvent;
import com.spring.security.authentication.event.publisher.infrastructure.event.custom.DefaultAuthenticationFailureEvent;
import com.spring.security.authentication.event.publisher.infrastructure.event.provider.CustomAuthenticationProvider;
import com.spring.security.authentication.event.publisher.infrastructure.exception.CustomException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringSecurityConfig(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user").hasAuthority("ROLE_USER")
                        .requestMatchers("/db").hasAuthority("ROLE_DB")
                        .requestMatchers("/admin").hasAuthority("ROLE_ADMIN")
                        .anyRequest().permitAll());

        http
                .formLogin(Customizer.withDefaults());

        http
                .cors(AbstractHttpConfigurer::disable);

        http
                .authenticationProvider(authenticationProvider());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new CustomAuthenticationProvider(authenticationEventPublisher());
    }

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(){
        Map<Class<? extends AuthenticationException>, Class<? extends AbstractAuthenticationFailureEvent>> mappings
                = new HashMap<>();

        mappings.put(CustomException.class, CustomAuthenticationFailureEvent.class);

        DefaultAuthenticationEventPublisher publisher = new DefaultAuthenticationEventPublisher(applicationEventPublisher);
        publisher.setAdditionalExceptionMappings(mappings);
        publisher.setDefaultAuthenticationFailureEvent(DefaultAuthenticationFailureEvent.class);

        return publisher;
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        UserDetails db = User.withUsername("db").password("{noop}1111").roles("DB").build();
        UserDetails admin = User.withUsername("admin").password("{noop}1111").roles("ADMIN", "SECURE").build();
        return new InMemoryUserDetailsManager(user, db, admin);
    }
}
