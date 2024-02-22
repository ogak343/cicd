package com.example.cicdpractice.repo;

import com.example.cicdpractice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByUsername(String username);

    Optional<Account> findByUsername(String reqDto);

    @NonNull
    Optional<Account> findById(@NonNull Long id);
}
