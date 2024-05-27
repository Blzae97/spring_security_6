package com.spring.security.custom.bean.authorization.infrastructure.component;

import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.stereotype.Component;

@Component(value = "customAuthorization")
public class CustomAuthorization {

    public boolean isUser(MethodSecurityExpressionOperations operations){
        return operations.hasAuthority("ROLE_USER");
    }

    public boolean isAdmin(MethodSecurityExpressionOperations operations){
        return operations.hasAuthority("ROLE_ADMIN");
    }
}
