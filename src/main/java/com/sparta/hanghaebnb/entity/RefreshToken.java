package com.sparta.hanghaebnb.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID",nullable = false)
    private User user;

    @Builder
    private RefreshToken(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public void updateRefreshToken(String newToken) {
        this.token = newToken;
    }

    public static RefreshToken of(String token, User user) {
        return RefreshToken.builder()
                .user(user)
                .token(token)
                .build();
    }
}
