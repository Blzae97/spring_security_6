package com.spring.security.custom.method.authorization.infrastructure.security.manager;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;

import java.util.function.Supplier;

public class CustomPreAuthorizationManager implements AuthorizationManager<MethodInvocation> {
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, MethodInvocation object) {
        Authentication auth = authentication.get();
        if (auth == null || auth instanceof AnonymousAuthenticationToken) {
            return new AuthorizationDecision(false);
        }

        return new AuthorizationDecision(auth.isAuthenticated());
    }
}
