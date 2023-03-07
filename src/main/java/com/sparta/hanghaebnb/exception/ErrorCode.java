package com.sparta.hanghaebnb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 400 Bad Request
    AUTHORIZATION(HttpStatus.BAD_REQUEST, "권한이 없습니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "유효한 토큰이 아닙니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다."),
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일입니다."),
    DUPLICATED_USER(HttpStatus.BAD_REQUEST, "중복된 가입정보가 있습니다."),
    DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "중복된 닉네임이 있습니다."),
    UNMATCHED_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),

    ALREADY_EXISTS_WISHLIST(HttpStatus.BAD_REQUEST, "이미 위시리스트에 추가하셨습니다"),
    Transition_Failed(HttpStatus.BAD_REQUEST, "MultipartFile -> File로 전환이 실패했습니다."),



    // 404 Not Found
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),
    NOT_FOUND_HOUSE(HttpStatus.NOT_FOUND, "해당 숙소를 찾을 수 없습니다."),
    NOT_FOUND_IMAGE(HttpStatus.NOT_FOUND, "해당 이미지를 찾을 수 없습니다."),

    NOT_FOUND_TOKEN(HttpStatus.NOT_FOUND, "토큰을 찾을 수 없습니다."),
    ALREADY_DELETED_WISHLIST(HttpStatus.NOT_FOUND, "이미 삭제된 위시리스트입니다."),
    NOT_FOUND_WISHLIST(HttpStatus.NOT_FOUND, "해당 위시리스타가 존재하지 않습니다.");



    private final HttpStatus httpStatus;
    private final String msg;
}
