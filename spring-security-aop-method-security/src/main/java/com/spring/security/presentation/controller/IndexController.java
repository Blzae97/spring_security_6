package com.spring.security.presentation.controller;

import com.spring.security.application.service.IndexService;
import com.spring.security.domain.dto.AccountItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/user")
    public String user() {
        return indexService.getUser();
    }

    @GetMapping(value = "/admin")
    public AccountItem admin(String name) {
        return indexService.getAdmin(name);
    }
}
