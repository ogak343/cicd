package com.example.cicdpractice.service;

import com.example.cicdpractice.dto.AuthReqDto;

public interface AuthService {
    Long create(AuthReqDto reqDto);

    String confirm(Long id, Integer code);

    String login(AuthReqDto reqDto);

    boolean valid(String username);
}
