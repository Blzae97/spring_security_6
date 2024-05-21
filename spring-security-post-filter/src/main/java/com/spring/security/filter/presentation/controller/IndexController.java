package com.spring.security.filter.presentation.controller;

import com.spring.security.filter.application.service.DataService;
import com.spring.security.filter.domain.Account;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class IndexController {
    private final DataService dataService;

    public IndexController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/readList")
    public List<Account> readList() {
        return dataService.readList();
    }

    @GetMapping("/readMap")
    public Map<String, Account> readMap() {
        return dataService.readMap();
    }

}
