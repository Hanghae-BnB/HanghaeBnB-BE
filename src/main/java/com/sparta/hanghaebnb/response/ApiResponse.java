package com.sparta.hanghaebnb.response;

import com.sparta.hanghaebnb.dto.MessageResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class ApiResponse {
    public MessageResponseDto success(String msg) {
        return MessageResponseDto.builder()
                .msg(msg)
                .statusCode(HttpStatus.OK)
                .build();
    }
}