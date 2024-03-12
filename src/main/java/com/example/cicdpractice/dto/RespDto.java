package com.example.cicdpractice.dto;

import lombok.Data;

@Data
public class RespDto<T> {

    private T result;

    public RespDto(T result) {
        this.result = result;
    }
}
