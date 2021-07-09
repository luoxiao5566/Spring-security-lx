package com.security.springsecurity.response;

import com.security.springsecurity.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private Error error;


    @Builder
    @Getter
    public static class Error {

        private ExceptionCode code;
        private String message;

        @Builder.Default
        private List<FieldError> details = new ArrayList<>();


        @Builder
        @Getter
        public static class FieldError {

            private String field;
            private String message;
        }

        @Override
        public String toString() {
            return reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    @Override
    public String toString() {
        return reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
