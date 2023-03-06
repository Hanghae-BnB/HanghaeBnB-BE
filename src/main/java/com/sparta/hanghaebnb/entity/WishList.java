package com.sparta.hanghaebnb.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user ;

    @OneToMany(mappedBy = "wishList", cascade = CascadeType.ALL)
    private List<WishListAndHouse> wishListAndHouseList = new ArrayList<>();

    @Builder
    private WishList(User user) {
        this.user = user;

    }

    public static WishList of(User user) {
        return WishList.builder()
                .user(user)
                .build();
    }

}
