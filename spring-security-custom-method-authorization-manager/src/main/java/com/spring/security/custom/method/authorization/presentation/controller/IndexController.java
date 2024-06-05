package com.spring.security.custom.method.authorization.presentation.controller;

import com.spring.security.custom.method.authorization.application.service.IndexService;
import com.spring.security.custom.method.authorization.domain.index.dto.AccountItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping("/user")
    public String user() {
        return indexService.getUser();
    }

    @GetMapping("/owner")
    public AccountItem owner(String name) {
        return indexService.getOwner(name);
    }

    @GetMapping("/display")
    public String display() {
        return indexService.display();
    }

}
