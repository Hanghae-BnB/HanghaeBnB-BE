package com.sparta.hanghaebnb.entity;

import com.sparta.hanghaebnb.dto.request.SignupRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private String email;


    @Builder
    private User(String username, String password, String birth, String email) {
        this.username = username;
        this.password = password;
        this.birth = birth;
        this.email = email;
    }

    public static User of(String username, String password, String birth, String email) {
        return builder()
                .username(username)
                .password(password)
                .birth(birth)
                .email(email)
                .build();
    }

    public static User from(SignupRequestDto signupRequestDto) {
        return builder()
                .email(signupRequestDto.getEmail())
                .password(signupRequestDto.getPassword())
                .birth(signupRequestDto.getBirth())
                .username(signupRequestDto.getUsername())
                .build();
    }


}
