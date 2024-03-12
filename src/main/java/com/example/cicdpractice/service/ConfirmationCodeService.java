package com.example.cicdpractice.service;

import com.example.cicdpractice.entity.ConfirmationCode;

public interface ConfirmationCodeService {
    ConfirmationCode getCode(Long id);

    ConfirmationCode save(ConfirmationCode code);
}
