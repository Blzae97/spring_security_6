package com.spring.security.authorize.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Account {
    private String owner;
    private boolean isSecure;

    @Builder
    public Account(String owner, boolean isSecure) {
        this.owner = owner;
        this.isSecure = isSecure;
    }
}
