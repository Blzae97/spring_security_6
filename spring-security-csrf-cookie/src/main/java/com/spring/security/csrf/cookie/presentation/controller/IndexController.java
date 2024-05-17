package com.spring.security.csrf.cookie.presentation.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @PostMapping("/csrf")
    public String csrf(){
        return "csrf 적용";
    }

    @PostMapping("/cookieCsrf")
    public CsrfToken cookieCsrf(CsrfToken csrfToken){
        return csrfToken;
    }
}
