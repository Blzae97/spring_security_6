package com.spring.security.csrf.presentation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @PostMapping(value = "/csrf")
    public String csrf(){
        return "CSRF OK";
    }
}
