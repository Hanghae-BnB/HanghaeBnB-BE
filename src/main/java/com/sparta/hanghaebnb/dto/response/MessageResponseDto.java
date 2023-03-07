package com.sparta.hanghaebnb.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MessageResponseDto {
    private String msg;
    private HttpStatus statusCode;
    @Builder
    public MessageResponseDto(String msg, HttpStatus statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
    public static MessageResponseDto of(String msg, HttpStatus status) {
        return MessageResponseDto.builder()
                .msg(msg)
                .statusCode(status)
                .build();
    }
}