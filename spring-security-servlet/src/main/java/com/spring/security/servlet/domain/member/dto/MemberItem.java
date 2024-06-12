package com.spring.security.servlet.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MemberItem {
    private String username;
    private String password;
}
