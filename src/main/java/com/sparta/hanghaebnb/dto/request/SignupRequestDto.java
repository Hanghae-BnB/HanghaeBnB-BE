package com.sparta.hanghaebnb.dto.request;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class SignupRequestDto {
    private String username;
    @Pattern(regexp = "^([a-zA-Z[0-9]]){8,15}$", message = "비밀번호는 8자 이상의 영대소문자, 숫자만 가능합니다.")
    private String password;
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$"
            , message = "이메일 형식에 맞지 않습니다.")
    private String email;
    private String birth;

    public void setPassword(String password) {
        this.password = password;
    }
}
