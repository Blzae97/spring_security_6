package com.spring.security.logout.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "/logout-success")
    public String logoutSuccess(){
        return "logout Success";
    }
}
