package com.spring.security.custom.request.matcher.infrastructure.security.manager;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.util.matcher.RequestMatcherEntry;

import java.util.List;
import java.util.function.Supplier;

public class CustomRequestMatcherDelegatingAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    private final RequestMatcherDelegatingAuthorizationManager manager;

    public CustomRequestMatcherDelegatingAuthorizationManager(List<RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>> mappingList) {
        manager = RequestMatcherDelegatingAuthorizationManager.builder()
                .mappings(m -> m.addAll(mappingList))
                .build();
    }

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {

        return manager.check(authentication, object.getRequest());
    }
}
