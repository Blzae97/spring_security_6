package com.spring.security.custom.authorization.manager.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping(value = "/index")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/secure")
    public String secure(){
        return "secure";
    }

}
