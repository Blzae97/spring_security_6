package com.spring.security.infrastructure.security.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

public class CustomMethodInterceptor implements MethodInterceptor {
    private final AuthorizationManager<MethodInvocation> authorizationManager;

    public CustomMethodInterceptor(AuthorizationManager<MethodInvocation> authorizationManager) {
        this.authorizationManager = authorizationManager;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(Objects.requireNonNull(authorizationManager.check(() -> authentication, invocation)).isGranted()){
            return invocation.proceed();
        }

        throw new AccessDeniedException("AccessDenied");
    }
}
