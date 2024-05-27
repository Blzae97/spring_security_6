package com.spring.security.jsr250.presentation.controller;

import jakarta.annotation.security.DenyAll;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @GetMapping("/")
    @PermitAll
    public String index(){
        return "index";
    }

    @GetMapping("/permit-all")
    @PermitAll
    public String permitAll(){
        return "permitAll";
    }

    @GetMapping("/user")
    @RolesAllowed("USER")
    public String user(){
        return "user";
    }

    @GetMapping("/db")
    @RolesAllowed("DB")
    public String db(){
        return "db";
    }

    @GetMapping("/admin")
    @RolesAllowed("ADMIN")
    public String admin(){
        return "admin";
    }

    @GetMapping("/deny-all")
    @DenyAll
    public String denyAll(){
        return "denyAll";
    }

}
