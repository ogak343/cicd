package com.example.cicdpractice.constants;

import lombok.Getter;

@Getter
public enum ErrorCode {
    CODE_EXPIRED(403),
    ACCOUNT_CONFIRMED(400),
    INVALID_CODE(400),
    ACCOUNT_NOT_FOUND(404),
    INVALID_PASSWORD(403),
    ACCOUNT_NOT_ENABLED(401),
    ACCOUNT_EXISTS(400);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }
}
