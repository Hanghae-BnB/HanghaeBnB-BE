package com.sparta.hanghaebnb.service;

import com.sparta.hanghaebnb.dto.MessageResponseDto;
import com.sparta.hanghaebnb.dto.SignupRequestDto;
import com.sparta.hanghaebnb.entity.User;
import com.sparta.hanghaebnb.repository.UserRepository;
import com.sparta.hanghaebnb.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ApiResponse apiResponse;
    public MessageResponseDto signup(SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        Optional<User> found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }
        User user = User.builder()
                .signupRequestDto(signupRequestDto)
                .build();
        userRepository.save(user);
        return apiResponse.success("회원가입 성공");
    }
}
