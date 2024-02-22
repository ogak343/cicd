package com.example.cicdpractice.service.impl;

import com.example.cicdpractice.entity.ConfirmationCode;
import com.example.cicdpractice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements NotificationService {

    private final JavaMailSender mailSender;

    @SneakyThrows
    @Override
    public void sendCode(ConfirmationCode code) {
        var message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);

        messageHelper.setTo(code.getAccount().getUsername());
        messageHelper.setText(String.valueOf(code.getCode()));

        mailSender.send(message);
    }
}
