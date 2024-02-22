package com.example.cicdpractice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthReqDto {
    @NotEmpty
    @Email
    private String username;
    @NotEmpty
    private String password;
}
