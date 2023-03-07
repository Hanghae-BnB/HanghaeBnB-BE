package com.sparta.hanghaebnb.controller;

import com.sparta.hanghaebnb.dto.request.LoginRequestDto;
import com.sparta.hanghaebnb.dto.response.LoginResponseDto;
import com.sparta.hanghaebnb.dto.response.MessageResponseDto;
import com.sparta.hanghaebnb.dto.request.SignupRequestDto;
import com.sparta.hanghaebnb.response.ApiDocumentResponse;
import com.sparta.hanghaebnb.security.UserDetailsImpl;
import com.sparta.hanghaebnb.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@ApiDocumentResponse
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @Operation(summary = "회원 가입 요청", description = "회원을 가입합니다.", tags = {"Users"})
    @PostMapping("/signup")
    public MessageResponseDto signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }
    @Operation(summary = "로그인 요청", description = "회원 로그인 진행", tags = {"Users"})
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }
    @Operation(summary = "로그아웃 요청", description = "회원 로그아웃 진행", tags = {"Users"})
    @PostMapping("/logout")
    public MessageResponseDto logout(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request) {
        return userService.logout(userDetails.getUser(), request);
    }
}
