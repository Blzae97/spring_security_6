package com.spring.security.request.cache.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "/")
    public String index(String customParam){
        if(customParam != null){
            return "custom-page";
        }else{
            return "index";
        }
    }
}
