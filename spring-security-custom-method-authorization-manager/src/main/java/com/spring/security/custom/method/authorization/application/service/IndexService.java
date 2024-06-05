package com.spring.security.custom.method.authorization.application.service;

import com.spring.security.custom.method.authorization.domain.index.dto.AccountItem;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class IndexService {
    @PreAuthorize("")
    public String getUser() {
        return "user";
    }

    @PostAuthorize("")
    public AccountItem getOwner(String name) {
        System.out.printf("name: %s", name);
        return new AccountItem(name, false);
    }

    public String display() {
        return "display";
    }
}
