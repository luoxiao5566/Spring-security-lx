package com.security.springsecurity.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class UnauthorizedException extends AuthenticationException {

    private final ExceptionCode code;

    public UnauthorizedException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
    }
}
