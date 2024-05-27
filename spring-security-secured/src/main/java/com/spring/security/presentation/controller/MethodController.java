package com.spring.security.presentation.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MethodController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    @Secured("ROLE_USER")
    public String user() {
        return "user";
    }

    @GetMapping("/db")
    @Secured("ROLE_DB")
    public String db() {
        return "db";
    }

    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public String admin() {
        return "admin";
    }

    @GetMapping("/admin-db")
    @Secured({"ROLE_ADMIN", "ROLE_DB"})
    public String aminAndDB() {
        return "aminAndDB";
    }
}
