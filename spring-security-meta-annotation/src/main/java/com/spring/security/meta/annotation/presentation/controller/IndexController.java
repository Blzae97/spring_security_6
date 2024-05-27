package com.spring.security.meta.annotation.presentation.controller;

import com.spring.security.meta.annotation.domain.dto.Account;
import com.spring.security.meta.annotation.infrastructure.annotation.IsAdmin;
import com.spring.security.meta.annotation.infrastructure.annotation.OwnerShip;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/admin")
    @IsAdmin
    public String isAdmin(){
        return "isAdmin";
    }

    @GetMapping("/ownership")
    @OwnerShip
    public Account ownerShip(String name){
        return new Account(name, false);
    }

}
