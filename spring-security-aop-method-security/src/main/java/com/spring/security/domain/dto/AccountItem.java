package com.spring.security.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AccountItem {
    private String name;
    private boolean isSecure;

    @Builder
    public AccountItem(String name, boolean isSecure) {
        this.name = name;
        this.isSecure = isSecure;
    }
}
