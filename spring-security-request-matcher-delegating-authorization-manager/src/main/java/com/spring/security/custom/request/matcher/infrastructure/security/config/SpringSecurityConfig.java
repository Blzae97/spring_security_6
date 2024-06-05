package com.spring.security.custom.request.matcher.infrastructure.security.config;

import com.spring.security.custom.request.matcher.infrastructure.security.manager.CustomRequestMatcherDelegatingAuthorizationManager;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcherEntry;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspect) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().access(authorizationManager(introspect))
                );

        http
                .formLogin(Customizer.withDefaults());

        http
                .cors(AbstractHttpConfigurer::disable);

        return http.build();
    }


    @Bean
    public AuthorizationManager<RequestAuthorizationContext> authorizationManager(HandlerMappingIntrospector introspect) {
        List<RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>> mappingList = new ArrayList<>();
        RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>> requestMatcherDBEntry = new RequestMatcherEntry<>(
                new MvcRequestMatcher(introspect, "/db"),
                AuthorityAuthorizationManager.hasAuthority("ROLE_DB")
        );

        RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>> requestMatcherAdminEntry = new RequestMatcherEntry<>(
                new MvcRequestMatcher(introspect, "/admin"),
                AuthorityAuthorizationManager.hasAuthority("ROLE_ADMIN")
        );

        RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>> anyRequestMatcher =
                new RequestMatcherEntry<>(AnyRequestMatcher.INSTANCE, new AuthenticatedAuthorizationManager<>());

        mappingList.add(requestMatcherDBEntry);
        mappingList.add(requestMatcherAdminEntry);
        mappingList.add(anyRequestMatcher);

        return new CustomRequestMatcherDelegatingAuthorizationManager(mappingList);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        UserDetails db = User.withUsername("db").password("{noop}1111").roles("DB").build();
        UserDetails admin = User.withUsername("admin").password("{noop}1111").roles("ADMIN", "SECURE").build();
        return new InMemoryUserDetailsManager(user, db, admin);
    }
}
