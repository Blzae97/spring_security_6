package com.spring.security.mvc.presentation.controller;

import com.spring.security.mvc.infrastructure.meta.CurrentUser;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    @GetMapping("/")
    public String index() {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        return trustResolver.isAnonymous(authentication) ? "anonymous" : "authenticated";
    }

    @GetMapping(value = "/info")
    public User user() {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        return authentication == null ? null : (User) authentication.getPrincipal();
    }

    @GetMapping(value = "/principal")
    public User principal(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping(value = "/current")
    public User currentUser(@CurrentUser User user) {
        return user;
    }

}
