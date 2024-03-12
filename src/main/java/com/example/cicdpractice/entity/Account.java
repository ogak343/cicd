package com.example.cicdpractice.entity;

import com.example.cicdpractice.constants.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Boolean enabled = false;
    @Enumerated(EnumType.STRING)
    private Role role;
}
