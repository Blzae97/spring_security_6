package com.spring.security.method.application.service;

import com.spring.security.method.domain.account.dto.AccountItem;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    public String getUser(){
        return "user";
    }

    public String getDB(){
        return "DB";
    }

    public AccountItem getAdmin(String name){
        System.out.println("관리자 접근");
        return new AccountItem(name, false);
    }
}
