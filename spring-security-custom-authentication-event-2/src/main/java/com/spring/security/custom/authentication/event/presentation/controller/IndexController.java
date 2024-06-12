package com.spring.security.custom.authentication.event.presentation.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "/")
    public Authentication index(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }
}
