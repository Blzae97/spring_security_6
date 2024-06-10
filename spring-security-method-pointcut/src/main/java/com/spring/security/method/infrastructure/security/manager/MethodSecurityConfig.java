package com.spring.security.method.infrastructure.security.manager;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity(prePostEnabled = false)
@Configuration
public class MethodSecurityConfig {

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Advisor roleAdminPointcut() {
        return new AuthorizationManagerBeforeMethodInterceptor(createCompositePointcut(), AuthorityAuthorizationManager.hasRole("ADMIN"));
    }

    private Pointcut createCompositePointcut() {
        AspectJExpressionPointcut getUserPointcut = new AspectJExpressionPointcut();
        getUserPointcut.setExpression("execution(* com.spring.security.method.application.service.IndexService.getUser(..))");

        AspectJExpressionPointcut getAdminPointCut = new AspectJExpressionPointcut();
        getAdminPointCut.setExpression("execution(* corm.spring.security.method.application.service.IndexService.getAdmin(..)))");

        ComposablePointcut composablePointcut = new ComposablePointcut((Pointcut) getUserPointcut);
        composablePointcut.union((Pointcut) getAdminPointCut);

        return composablePointcut;
    }


}
