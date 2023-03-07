package com.sparta.hanghaebnb.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String username;

    @Builder
    private LoginResponseDto(String username) {
        this.username = username;
    }

    public static LoginResponseDto of(String username) {
        return builder().username(username).build();
    }
}
