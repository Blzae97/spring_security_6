package com.spring.security.custom.authorization.manager.infrastructure.security.custom;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.function.Supplier;

public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private static final String REQUIRED_ROLE = "ROLE_SECURE";

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        Authentication auth = authentication.get();

        if(auth == null || !auth.isAuthenticated()){
            return new AuthorizationDecision(false);
        }

        boolean hasRequiredNew = auth.getAuthorities().stream()
                .anyMatch(g -> g.getAuthority().equals(REQUIRED_ROLE));

        return new AuthorizationDecision(hasRequiredNew);
    }

}
