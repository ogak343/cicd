package com.example.cicdpractice.service;

import com.example.cicdpractice.entity.Account;

public interface JwtService {
    String generateToken(Account account);

    void authorize(String string);
}
