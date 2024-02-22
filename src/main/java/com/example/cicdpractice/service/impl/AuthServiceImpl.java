package com.example.cicdpractice.service.impl;

import com.example.cicdpractice.configuration.exception.CustomException;
import com.example.cicdpractice.constants.ErrorCode;
import com.example.cicdpractice.constants.Role;
import com.example.cicdpractice.dto.AuthReqDto;
import com.example.cicdpractice.entity.Account;
import com.example.cicdpractice.entity.ConfirmationCode;
import com.example.cicdpractice.repo.AccountRepository;
import com.example.cicdpractice.service.AuthService;
import com.example.cicdpractice.service.ConfirmationCodeService;
import com.example.cicdpractice.service.JwtService;
import com.example.cicdpractice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountRepository repository;
    private final PasswordEncoder encoder;
    private final ConfirmationCodeService confirmationCodeService;
    private final JwtService jwtService;
    private final NotificationService notificationService;

    @Override
    @Transactional
    public Long create(AuthReqDto reqDto) {

        if (valid(reqDto.getUsername())) {
            throw CustomException.error(ErrorCode.ACCOUNT_EXISTS);
        }
        var model = new Account();
        model.setUsername(reqDto.getUsername());
        model.setPassword(encoder.encode(reqDto.getPassword()));
        model.setRole(Role.USER);
        model = repository.save(model);
        var code = new ConfirmationCode(model);
        notificationService.sendCode(code);
        return confirmationCodeService.save(code).getAccount().getId();
    }

    @Override
    @Transactional
    public String confirm(Long id, Integer code) {

        var cCode = confirmationCodeService.getCode(id);

        if (cCode.getExpiredAt().isBefore(OffsetDateTime.now()) || cCode.getConfirmedAt() != null) {
            throw CustomException.error(ErrorCode.CODE_EXPIRED);
        }

        if (!Objects.equals(cCode.getCode(), code)) {
            throw CustomException.error(ErrorCode.INVALID_CODE);
        }

        if (cCode.getAccount().getEnabled()) {
            throw CustomException.error(ErrorCode.ACCOUNT_CONFIRMED);
        }
        cCode.getAccount().setEnabled(true);
        cCode.setConfirmedAt(OffsetDateTime.now());
        return jwtService.generateToken(confirmationCodeService.save(cCode).getAccount());
    }

    @Override
    public String login(AuthReqDto reqDto) {

        var account = repository.findByUsername(reqDto.getUsername())
                .orElseThrow(()-> CustomException.error(ErrorCode.ACCOUNT_NOT_FOUND));

        if (!encoder.matches(reqDto.getPassword(), account.getPassword())) {
            throw CustomException.error(ErrorCode.INVALID_PASSWORD);
        }

        if (!account.getEnabled()) {
            throw CustomException.error(ErrorCode.ACCOUNT_NOT_ENABLED);
        }

        return jwtService.generateToken(account);
    }

    @Override
    public boolean valid(String username) {
        return repository.existsByUsername(username);
    }
}
