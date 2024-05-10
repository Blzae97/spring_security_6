package com.spring.security.login.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "/home")
    public String home(){
        return "home";
    }

    @GetMapping(value = "failed")
    public String failed(){
        return "failed";
    }
}
