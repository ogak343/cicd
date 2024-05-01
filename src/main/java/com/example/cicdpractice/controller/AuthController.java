package com.example.cicdpractice.controller;

import com.example.cicdpractice.dto.AuthReqDto;
import com.example.cicdpractice.dto.AuthRespDto;
import com.example.cicdpractice.dto.RespDto;
import com.example.cicdpractice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;


    @PostMapping("/create")
    public ResponseEntity<RespDto<Long>> create(@Valid @RequestBody AuthReqDto reqDto) {

        log.info("[accountCreate] : {}", reqDto);

        return ResponseEntity.ok(new RespDto<>(service.create(reqDto)));
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<AuthRespDto> confirm(@PathVariable("id") Long id,
                                               @RequestParam(value = "code") Integer code) {

        log.info("[accountConfirm] id : {}, code : {}", id, code);

        return ResponseEntity.ok(new AuthRespDto(service.confirm(id, code)));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthRespDto> login(@Valid @RequestBody AuthReqDto reqDto) {

        log.info("[login] : {}", reqDto);

        return ResponseEntity.ok(new AuthRespDto(service.login(reqDto)));
    }

    @GetMapping("/valid/{username}")
    public ResponseEntity<RespDto<Boolean>> valid(@PathVariable("username") String username) {

        log.info("[valid] username : {}", username);

        return ResponseEntity.ok(new RespDto<>(service.valid(username)));
    }
}
