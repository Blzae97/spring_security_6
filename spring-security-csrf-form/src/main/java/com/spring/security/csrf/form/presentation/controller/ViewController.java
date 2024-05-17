package com.spring.security.csrf.form.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping(value = "/form")
    public String form(){
        return "form";
    }
}
