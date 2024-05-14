package com.spring.security.session.presentation.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "/")
    public Authentication index(Authentication authentication){
        return authentication;
    }

    @GetMapping(value = "/invalid-session")
    public Authentication invalidSession(Authentication authentication){
        return authentication;
    }

    @GetMapping(value = "/expired")
    public Authentication expired(Authentication authentication){
        return authentication;
    }

}
