package com.spring.security.custom.method.authorization.infrastructure.security.config;

import com.spring.security.custom.method.authorization.infrastructure.security.manager.CustomPostAuthorizationManager;
import com.spring.security.custom.method.authorization.infrastructure.security.manager.CustomPreAuthorizationManager;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authorization.method.AuthorizationManagerAfterMethodInterceptor;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity(prePostEnabled = false)
@Configuration
public class MethodSecurityConfig {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Advisor preAuthorize(){
        return AuthorizationManagerBeforeMethodInterceptor.preAuthorize(new CustomPreAuthorizationManager());
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Advisor postAuthorize(){
        return AuthorizationManagerAfterMethodInterceptor.postAuthorize(new CustomPostAuthorizationManager());
    }
}
