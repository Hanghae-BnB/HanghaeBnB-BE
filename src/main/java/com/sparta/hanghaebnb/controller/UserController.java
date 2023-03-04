package com.sparta.hanghaebnb.controller;

import com.sparta.hanghaebnb.dto.MessageResponseDto;
import com.sparta.hanghaebnb.dto.SignupRequestDto;
import com.sparta.hanghaebnb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public MessageResponseDto signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }
}
