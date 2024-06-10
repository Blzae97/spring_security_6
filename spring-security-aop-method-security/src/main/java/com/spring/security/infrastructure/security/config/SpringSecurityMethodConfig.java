package com.spring.security.infrastructure.security.config;

import com.spring.security.infrastructure.security.interceptor.CustomMethodInterceptor;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.parameters.P;

import java.lang.reflect.Method;

@EnableMethodSecurity
@Configuration
public class SpringSecurityMethodConfig {

    @Bean
    public Pointcut pointcut(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.spring.security.application.service.IndexService.*(..))");
        return pointcut;
    }

    @Bean
    public MethodInterceptor customMethodInterceptor() {
//        AuthenticatedAuthorizationManager<MethodInvocation> authenticated = AuthenticatedAuthorizationManager.authenticated();
        AuthorityAuthorizationManager<MethodInvocation> admin = AuthorityAuthorizationManager.hasRole("ADMIN");
        return new CustomMethodInterceptor(admin);
    }

    @Bean
    public Advisor serviceAdvisor(){

        return new DefaultPointcutAdvisor(pointcut(), customMethodInterceptor());
    }



}

