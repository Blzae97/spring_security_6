package com.spring.security.custom.bean.authorization.presentation.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping(value = "/is-user")
    @PreAuthorize(value = "@customAuthorization.isUser(#root)")
    public String isUSer(){
        return "isUser";
    }

    @GetMapping(value = "/is-admin")
    @PreAuthorize(value = "@customAuthorization.isAdmin(#root)")
    public String isAdmin(){
        return "isAdmin";
    }
}
