package com.spring.security.custom.request.matcher.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping(value = "/")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/db")
    public String db(){
        return "db";
    }

    @GetMapping(value = "/admin")
    public String admin(){
        return "admin";
    }

}
