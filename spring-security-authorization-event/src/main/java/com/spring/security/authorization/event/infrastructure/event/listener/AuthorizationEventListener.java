package com.spring.security.authorization.event.infrastructure.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.security.authorization.event.AuthorizationEvent;
import org.springframework.security.authorization.event.AuthorizationGrantedEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationEventListener {

    @EventListener
    public void onAuthorization(AuthorizationEvent event){
        System.out.println("event= " + event.getAuthentication().get().getAuthorities());
    }

    @EventListener
    public void onAuthorization(AuthorizationDeniedEvent<?> event){
        System.out.println("event= " + event.getAuthentication().get().getAuthorities());
    }

    @EventListener
    public void onAuthorization(AuthorizationGrantedEvent<?> event) {
        System.out.println("event= " + event.getAuthentication().get().getAuthorities());
    }
}
