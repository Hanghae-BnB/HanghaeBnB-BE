package com.sparta.hanghaebnb.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.hanghaebnb.dto.response.MessageResponseDto;
import com.sparta.hanghaebnb.entity.RefreshToken;
import com.sparta.hanghaebnb.exception.CustomException;
import com.sparta.hanghaebnb.exception.ErrorCode;
import com.sparta.hanghaebnb.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.resolveToken(request);
        String refresh = jwtUtil.resolveRefreshToken(request);
        if (token != null && refresh != null) {
            if (!jwtUtil.validateToken(token)) { // access토큰이 유효하지 않지만
                if (!jwtUtil.validateToken(refresh)) { // refresh 토큰이 유효하면
                    jwtExceptionHandler(response, "Token Error", HttpStatus.UNAUTHORIZED);
                    return;
                } else {
                    // refresh토큰과 같은 토큰을 db에서 찾아온후
                    Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken("Bearer " + refresh);
                    if (refreshToken.isEmpty()) {
                        throw new CustomException(ErrorCode.INVALID_REFRESH_TOKEN);
                    }
                    //access토큰을 재발급
                    response.addHeader(JwtUtil.AT_HEADER, jwtUtil.createAT(refreshToken.get().getUser().getEmail()));
                }
            }
            Claims info = jwtUtil.getUserInfoFromToken(refresh);
            setAuthentication(info.getSubject());
        }
        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtUtil.createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    public void jwtExceptionHandler(HttpServletResponse response, String msg, HttpStatus statusCode) {
        response.setStatus(statusCode.value());
        response.setContentType("application/json");
        try {
            String json = new ObjectMapper().writeValueAsString(new MessageResponseDto(msg, statusCode));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


}