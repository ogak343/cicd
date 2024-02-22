package com.example.cicdpractice.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AuthRespDto {
    private HttpStatus status;
    private String token;

    public AuthRespDto(String token) {
        this.status = HttpStatus.OK;
        this.token = token;
    }
}
