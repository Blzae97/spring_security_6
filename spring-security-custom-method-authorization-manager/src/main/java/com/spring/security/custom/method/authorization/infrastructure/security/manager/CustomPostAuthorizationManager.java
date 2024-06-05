package com.spring.security.custom.method.authorization.infrastructure.security.manager;

import com.spring.security.custom.method.authorization.domain.index.dto.AccountItem;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.method.MethodInvocationResult;
import org.springframework.security.core.Authentication;

import java.util.function.Supplier;

public class CustomPostAuthorizationManager implements AuthorizationManager<MethodInvocationResult> {

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, MethodInvocationResult object) {
        Authentication auth = authentication.get();
        if(auth == null || auth instanceof AnonymousAuthenticationToken){
            return new AuthorizationDecision(false);
        }

        AccountItem item = (AccountItem) object.getResult();
        boolean isGranted = item.getOwner().equals(auth.getName());
        return new AuthorizationDecision(isGranted);
    }
}
