package com.spring.security.exception.handling.presentation.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "/")
    public Authentication index(Authentication authentication){
        return authentication;
    }

    @GetMapping(value = "/authentication/exception")
    public String authenticationException(){
        return "authenticationException";
    }

    @GetMapping(value = "/authorization/exception")
    public String authorizationException(){
        return "authorizationException";
    }


}
