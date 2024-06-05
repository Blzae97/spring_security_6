package com.spring.security.custom.method.authorization.domain.index.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AccountItem {
    private String owner;
    private boolean isSecure;

    @Builder
    public AccountItem(String owner, boolean isSecure) {
        this.owner = owner;
        this.isSecure = isSecure;
    }
}
