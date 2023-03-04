package com.sparta.hanghaebnb.repository;

import com.sparta.hanghaebnb.entity.RefreshToken;
import com.sparta.hanghaebnb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByUser(User user);

    Optional<RefreshToken> findByToken(String token);
}
