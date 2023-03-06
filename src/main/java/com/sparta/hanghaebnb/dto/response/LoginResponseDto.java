package com.sparta.hanghaebnb.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String username;

    @Builder
    public LoginResponseDto(String username) {
        this.username = username;
    }
}
