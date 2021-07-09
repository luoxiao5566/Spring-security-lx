package com.security.springsecurity.exception;

import com.security.springsecurity.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(ExceptionAdvice.BASE_ADVICE_ORDER)
public class ExceptionAdvice {

    public static final int BASE_ADVICE_ORDER = 200;

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handle(UnauthorizedException exception) {
        log.error("unauthorized exception caught:", exception);
        return error(exception.getCode());
    }

    private ErrorResponse error(ExceptionCode code, Object... args) {
        ErrorResponse.Error error = ErrorResponse.Error.builder()
                .code(code)
                .message(StringUtils.join(args, "|"))
                .build();
        return new ErrorResponse(error);
    }
}
