package com.sparta.hanghaebnb.service;

import com.sparta.hanghaebnb.dto.LoginRequestDto;
import com.sparta.hanghaebnb.dto.LoginResponseDto;
import com.sparta.hanghaebnb.dto.MessageResponseDto;
import com.sparta.hanghaebnb.dto.SignupRequestDto;
import com.sparta.hanghaebnb.entity.RefreshToken;
import com.sparta.hanghaebnb.entity.User;
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
        String username = signupRequestDto.getUsername();
        String email = signupRequestDto.getEmail();
        String birth = signupRequestDto.getBirth();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        Optional<User> found = userRepository.findByEmail(email);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }
        User user = User.of(username, password, birth, email);
        userRepository.save(user);
        return apiResponse.success("회원가입 성공");
    }
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalArgumentException("회원을 찾을 수 없습니다.")
        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }
        RefreshToken refreshToken = RefreshToken.builder()
                .token(jwtUtil.createRT(user.getEmail()))
                .user(user)
                .build();
        Optional<RefreshToken> found = refreshTokenRepository.findByUser(user);
        //리프레시 토큰 발급
        String rt = jwtUtil.createRT(user.getEmail());

        if (found.isEmpty()) {
            refreshTokenRepository.save(refreshToken);
        } else {
            found.get().updateRefreshToken(rt);
        }

        //액세스 토큰 response
        response.addHeader(JwtUtil.AT_HEADER, jwtUtil.createAT(user.getEmail()));
        //리프레시 토큰 response
        response.addHeader(JwtUtil.RT_HEADER,rt);
        return LoginResponseDto.builder().username(user.getUsername()).build();
    }

    public MessageResponseDto logout(User user) {
        User userCheck = userRepository.findByEmail(user.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("회원이 존재하지 않습니다.")
        );

        return apiResponse.success("로그아웃 성공");
    }
    @Transactional
    public MessageResponseDto refresh(HttpServletRequest request, HttpServletResponse response) {
        String requestRT = request.getHeader(JwtUtil.RT_HEADER);
        Optional<RefreshToken> found = refreshTokenRepository.findByToken(requestRT);
        if (found.isEmpty()) {
            throw new IllegalArgumentException("토큰이 존재하지 않습니다.");
        }
        response.addHeader(JwtUtil.AT_HEADER, jwtUtil.createAT(found.get().getUser().getEmail()));
        return apiResponse.success("토큰 발급을 성공했습니다.");
    }
}
