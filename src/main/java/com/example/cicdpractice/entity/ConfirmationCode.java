package com.example.cicdpractice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Random;

@Entity
@Table(name = "confirmation_code")
@Getter
@Setter
public class ConfirmationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer code;
    private OffsetDateTime createdAt;
    private OffsetDateTime confirmedAt;
    private OffsetDateTime expiredAt;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public ConfirmationCode(Account account) {
        this.code = new Random().nextInt(100000, 999999);
        this.createdAt = OffsetDateTime.now();
        this.expiredAt = OffsetDateTime.now().plusMinutes(5);
        this.account = account;
    }

    public ConfirmationCode() {

    }
}
