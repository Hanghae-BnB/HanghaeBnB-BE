package com.sparta.hanghaebnb.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class SignupRequestDto {
    private String username;
    @Pattern(regexp ="^([a-zA-Z[0-9]]){8,15}$", message = "비밀번호는 8자 이상의 영대소문자, 숫자만 가능합니다.")
    private String password;
    private String email;
    private String birth;
}
