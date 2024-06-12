package com.spring.security.custom.authentication.event.infrastructure.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomException extends AuthenticationException {
    public CustomException(String explanation) {
        super(explanation);
    }
}
