package com.example.cicdpractice.repo;

import com.example.cicdpractice.entity.ConfirmationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Long> {
    Optional<ConfirmationCode> findByAccountId(Long id);
}
