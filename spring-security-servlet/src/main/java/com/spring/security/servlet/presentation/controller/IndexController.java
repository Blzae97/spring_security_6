package com.spring.security.servlet.presentation.controller;

import com.spring.security.servlet.domain.member.dto.MemberItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
public class IndexController {
    @GetMapping("/")
    public String index(HttpServletRequest request){
        return "index";
    }
    @GetMapping("/user")
    public String user(){
        return "user";
    }
    @GetMapping("/db")
    public String db(){
        return "db";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, MemberItem item) throws ServletException, IOException {
        request.login(item.getUsername(), item.getPassword());
        System.out.println("login is successful");
        return "login";
    }

    @GetMapping("/users")
    public List<MemberItem> users(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authenticate = request.authenticate(response);
        if (authenticate) {
            return List.of(new MemberItem("user","1111"));
        }
        return Collections.emptyList();
    }
}
