package com.example.cicdpractice.configuration.exception;

import com.example.cicdpractice.constants.ErrorCode;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class CustomException extends ErrorResponseException {
    public CustomException(ProblemDetail detail) {
        super(HttpStatusCode.valueOf(detail.getStatus()), detail, null);
    }

    public static CustomException error(ErrorCode errorCode) {
        return new CustomException(ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(errorCode.getCode()), errorCode.name()));
    }
}
