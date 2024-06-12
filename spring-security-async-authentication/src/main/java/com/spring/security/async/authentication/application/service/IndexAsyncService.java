package com.spring.security.async.authentication.application.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class IndexAsyncService {

    @Async
    public void asyncAuthentication(){
        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy().getContext();
        System.out.println("securityContext = " + securityContext);
        System.out.println("Child Thread: " + Thread.currentThread().getName());
        Authentication authentication = securityContext.getAuthentication();
        System.out.println(authentication);
    }
}
