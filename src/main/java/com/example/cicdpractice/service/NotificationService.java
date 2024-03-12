package com.example.cicdpractice.service;

import com.example.cicdpractice.entity.ConfirmationCode;

public interface NotificationService {
    void sendCode(ConfirmationCode code);
}
