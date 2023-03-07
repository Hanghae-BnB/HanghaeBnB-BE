package com.sparta.hanghaebnb.service;

import com.sparta.hanghaebnb.dto.request.LoginRequestDto;
import com.sparta.hanghaebnb.dto.response.LoginResponseDto;
import com.sparta.hanghaebnb.dto.response.MessageResponseDto;
import com.sparta.hanghaebnb.dto.request.SignupRequestDto;
import com.sparta.hanghaebnb.entity.RefreshToken;
import com.sparta.hanghaebnb.entity.User;
import com.sparta.hanghaebnb.exception.CustomException;
import com.sparta.hanghaebnb.exception.ErrorCode;
import com.sparta.hanghaebnb.jwt.JwtUtil;
import com.sparta.hanghaebnb.repository.RefreshTokenRepository;
import com.sparta.hanghaebnb.repository.UserRepository;
import com.sparta.hanghaebnb.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final ApiResponse apiResponse;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;

    public MessageResponseDto signup(SignupRequestDto signupRequestDto) {
        String email = signupRequestDto.getEmail();
        signupRequestDto.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        Optional<User> found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
        User user = User.from(signupRequestDto);
        userRepository.save(user);
        return apiResponse.success("회원가입 성공");
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomException(ErrorCode.UNMATCHED_PASSWORD);
        }
        RefreshToken refreshToken = RefreshToken.of(jwtUtil.createRT(user.getEmail()), user);
        Optional<RefreshToken> found = refreshTokenRepository.findByUser(user);
        //리프레시 토큰 발급
        String rt = jwtUtil.createRT(user.getEmail());
        //refreshToken이 DB에 있는지 확인 후 없으면 저장, 있으면 수정
        isSavedRefreshToken(refreshToken, found, rt);

        //액세스 토큰 response
        response.addHeader(JwtUtil.AT_HEADER, jwtUtil.createAT(user.getEmail()));
        //리프레시 토큰 response
        response.addHeader(JwtUtil.RT_HEADER, rt);
        return LoginResponseDto.builder().username(user.getUsername()).build();
    }


    @Transactional
    public MessageResponseDto logout(User user, HttpServletRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByUser(user).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_USER)
        );
        if (!Objects.equals(refreshToken.getToken(), request.getHeader("RT_Authorization"))) {
            throw new CustomException(ErrorCode.INVALID_REFRESH_TOKEN);
        }
        refreshTokenRepository.deleteByToken(request.getHeader("RT_Authorization"));
        return apiResponse.success("로그아웃 성공");
    }

    public MessageResponseDto refresh(HttpServletRequest request, HttpServletResponse response, User user) {
        String requestRT = request.getHeader(JwtUtil.RT_HEADER);
        Optional<RefreshToken> found = refreshTokenRepository.findByToken(requestRT);
        if (found.isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        response.addHeader(JwtUtil.AT_HEADER, jwtUtil.createAT(found.get().getUser().getEmail()));
        return apiResponse.success("토큰 발급을 성공했습니다.");
    }

    private void isSavedRefreshToken(RefreshToken refreshToken, Optional<RefreshToken> found, String rt) {
        if (found.isEmpty()) {
            refreshTokenRepository.save(refreshToken);
        } else {
            found.get().updateRefreshToken(rt);
        }
    }
}