package com.spring.security.login.presentation.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "/anonymous")
    public String anonymous() {
        return "anonymous";
    }

    @GetMapping(value = "/authentication")
    public String authentication(Authentication authentication) {
        if(authentication == null){
            return "Authentication 값이 NULL 입니다.";
        }

        System.out.println(authentication.getPrincipal());
        return authentication.getClass().getSimpleName();
    }

    @GetMapping(value = "/anonymous-context")
    public String anonymousContext(@CurrentSecurityContext SecurityContext securityContext) {
        Authentication authentication = securityContext.getAuthentication();
        System.out.println(authentication.getPrincipal());
        return authentication.getClass().getSimpleName();
    }

}

