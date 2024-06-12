package com.spring.security.async.authentication.presentation.controller;

import com.spring.security.async.authentication.application.service.IndexAsyncService;
import com.spring.security.async.authentication.infrastructure.async.AuthenticationCallable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class IndexController {
    private final IndexAsyncService indexAsyncService;

    public IndexController(IndexAsyncService indexAsyncService) {
        this.indexAsyncService = indexAsyncService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/db")
    public String db() {
        return "db";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/callable")
    public Callable<Authentication> callable() {
        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy().getContext();
        System.out.println("securityContext = " + securityContext);
        System.out.println("Parent Thread: " + Thread.currentThread().getName());

        return new AuthenticationCallable();
    }

    @GetMapping(value = "/async")
    public Authentication async() {
        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy().getContext();
        System.out.println("securityContext = " + securityContext);
        System.out.println("Parent Thread: " + Thread.currentThread().getName());
        indexAsyncService.asyncAuthentication();
        return securityContext.getAuthentication();
    }
}
