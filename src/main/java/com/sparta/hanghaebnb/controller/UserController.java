package com.sparta.hanghaebnb.controller;

import com.sparta.hanghaebnb.dto.LoginRequestDto;
import com.sparta.hanghaebnb.dto.LoginResponseDto;
import com.sparta.hanghaebnb.dto.MessageResponseDto;
import com.sparta.hanghaebnb.dto.SignupRequestDto;
import com.sparta.hanghaebnb.response.ApiDocumentResponse;
import com.sparta.hanghaebnb.security.UserDetailsImpl;
import com.sparta.hanghaebnb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
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

    @PostMapping("/signup")
    public MessageResponseDto signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }

    @PostMapping("/logout")
    public MessageResponseDto logout(@AuthenticationPrincipal UserDetailsImpl userDetails,HttpServletRequest request) {
        return userService.logout(userDetails.getUser(),request);
    }

    @PostMapping("/refresh")
    public MessageResponseDto refresh(HttpServletRequest request, HttpServletResponse response, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.refresh(request, response,userDetails.getUser());
    }
}
