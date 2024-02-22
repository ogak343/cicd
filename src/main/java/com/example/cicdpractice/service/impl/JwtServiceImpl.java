package com.example.cicdpractice.service.impl;

import com.example.cicdpractice.configuration.exception.CustomException;
import com.example.cicdpractice.constants.ErrorCode;
import com.example.cicdpractice.constants.Role;
import com.example.cicdpractice.entity.Account;
import com.example.cicdpractice.repo.AccountRepository;
import com.example.cicdpractice.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final AccountRepository accountRepository;

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.access}")
    private Long accessDuration;
    @Override
    public String generateToken(Account account) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(String.valueOf(account.getId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessDuration * 1000 * 60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public void authorize(String header) {

        var userId = extractUsername(header.substring(7));

        if (userId != null) {

            var user = accountRepository.findById(Long.valueOf(userId))
                    .orElseThrow(() ->
                            CustomException.error(ErrorCode.ACCOUNT_NOT_FOUND));

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user.getId(),
                    null,
                    getAuthorities(user.getRole())
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

    private String extractUsername(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.name());
        return Collections.singletonList(authority);
    }
}
