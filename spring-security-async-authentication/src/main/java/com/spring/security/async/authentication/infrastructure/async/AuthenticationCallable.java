package com.spring.security.async.authentication.infrastructure.async;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.concurrent.Callable;

public class AuthenticationCallable implements Callable<Authentication> {

    @Override
    public Authentication call() {
        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy().getContext();
        System.out.println("securityContext = " + securityContext);
        System.out.println("Child Thread: " + Thread.currentThread().getName());

        return securityContext.getAuthentication();
    }
}
