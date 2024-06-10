package com.spring.security.application.service;

import com.spring.security.domain.dto.AccountItem;
import org.springframework.stereotype.Service;

@Service
public class IndexService {
    public String getUser() {
        return "user";
    }

    public AccountItem getAdmin(String name) {
        System.out.println("관리자 접근");
        return new AccountItem(name, false);
    }
}
