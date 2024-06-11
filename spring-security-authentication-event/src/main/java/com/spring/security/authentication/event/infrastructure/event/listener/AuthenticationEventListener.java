package com.spring.security.authentication.event.infrastructure.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.*;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEventListener {

    @EventListener
    public void onSuccess(AbstractAuthenticationEvent event){
        System.out.println("AbstractAuthenticationEvent= " + event.getAuthentication().getName());
    }

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent event){
        System.out.println("AuthenticationSuccessEvent= " + event.getAuthentication().getName());
    }

    @EventListener
    public void onSuccess(InteractiveAuthenticationSuccessEvent event){
        System.out.println("InteractiveAuthenticationSuccessEvent= " + event.getAuthentication().getName());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent event){
        System.out.println("AbstractAuthenticationFailureEvent= " + event.getException().getMessage());
    }

    @EventListener
    public void onFailure(AuthenticationFailureBadCredentialsEvent event){
        System.out.println("AuthenticationFailureBadCredentialsEvent= " + event.getException().getMessage());
    }

    @EventListener
    public void onFailure(AuthenticationFailureProviderNotFoundEvent event){
        System.out.println("AuthenticationFailureProviderNotFoundEvent= " + event.getException().getMessage());
    }
}
