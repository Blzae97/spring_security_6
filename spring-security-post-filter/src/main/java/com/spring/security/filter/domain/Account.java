package com.spring.security.filter.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Account {
    private String owner;
    private boolean isSecure;

    @Builder
    public Account(String owner, boolean isSecure) {
        this.owner = owner;
        this.isSecure = isSecure;
    }
}