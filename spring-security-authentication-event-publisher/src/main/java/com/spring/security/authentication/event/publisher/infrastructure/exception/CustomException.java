package com.spring.security.authentication.event.publisher.infrastructure.exception;

import org.springframework.security.core.AuthenticationException;

public class CustomException extends AuthenticationException {
    public CustomException(String explanation) {
        super(explanation);
    }
}
