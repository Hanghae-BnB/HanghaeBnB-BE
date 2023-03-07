package com.sparta.hanghaebnb.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "wish")
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOUSE_ID",nullable = false)
    private House house;

    @Builder
    private Wish(User user, House house) {
        this.user = user;
        this.house = house;
    }

    public static Wish of(User user, House house) {
        return Wish.builder()
                .user(user)
                .house(house)
                .build();
    }
}
