package com.example.cicdpractice.service.impl;

import com.example.cicdpractice.configuration.exception.CustomException;
import com.example.cicdpractice.constants.ErrorCode;
import com.example.cicdpractice.entity.ConfirmationCode;
import com.example.cicdpractice.repo.ConfirmationCodeRepository;
import com.example.cicdpractice.service.ConfirmationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmationCodeServiceImpl implements ConfirmationCodeService {

    private final ConfirmationCodeRepository repository;

    @Override
    public ConfirmationCode getCode(Long id) {
        return repository.findByAccountId(id)
                .orElseThrow(() -> CustomException.error(ErrorCode.ACCOUNT_NOT_FOUND));
    }

    @Override
    public ConfirmationCode save(ConfirmationCode code) {
        return repository.save(code);
    }
}
