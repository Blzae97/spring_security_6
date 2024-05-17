package com.spring.security.cors.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class CorsController {

    @GetMapping(value = "/users")
    public String users(){
        return "{\"name\":\"hong gil dong\"}";
    }
}
