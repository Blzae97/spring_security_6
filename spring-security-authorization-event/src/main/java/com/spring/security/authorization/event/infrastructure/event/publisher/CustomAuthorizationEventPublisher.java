package com.spring.security.authorization.event.infrastructure.event.publisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authorization.AuthorityAuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationEventPublisher;
import org.springframework.security.authorization.event.AuthorizationGrantedEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.function.Supplier;

public class CustomAuthorizationEventPublisher implements AuthorizationEventPublisher {
    private final AuthorizationEventPublisher authorizationEventPublisher;
    private final ApplicationEventPublisher applicationEventPublisher;

    public CustomAuthorizationEventPublisher(AuthorizationEventPublisher authorizationEventPublisher,
                                             ApplicationEventPublisher applicationEventPublisher) {
        this.authorizationEventPublisher = authorizationEventPublisher;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public <T> void publishAuthorizationEvent(Supplier<Authentication> authentication,
                                              T object,
                                              AuthorizationDecision decision) {
        if(decision == null){
            return;
        }

        if(!decision.isGranted()){
            authorizationEventPublisher.publishAuthorizationEvent(authentication, object, decision);
            return;
        }

        boolean eventBePublished = shouldThisEventBePublished(decision);
        if(eventBePublished){
            AuthorizationGrantedEvent<T> authorizationGrantedEvent = new AuthorizationGrantedEvent<>(authentication, object, decision);
            applicationEventPublisher.publishEvent(authorizationGrantedEvent);
        }

    }

    private boolean shouldThisEventBePublished(AuthorizationDecision decision){
        if(!(decision instanceof AuthorityAuthorizationDecision)){
            return false;
        }

        Collection<GrantedAuthority> authorities = ((AuthorityAuthorizationDecision) decision).getAuthorities();
        for(GrantedAuthority authority : authorities){
            if(authority.getAuthority().equals("ROLE_ADMIN")){
                return true;
            }
        }

        return false;
    }
}
