package com.spring.security.csrf.form.presentation.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @PostMapping("/csrf")
    public String csrf(){
        return "csrf 적용";
    }

    @PostMapping("/formCsrf")
    public CsrfToken formCsrf(CsrfToken csrfToken, String username, String password){
        return csrfToken;
    }
}
