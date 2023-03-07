package com.sparta.hanghaebnb.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class WishListAndHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "WISHLIST_ID",nullable = false)
//    private WishList wishList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOUSE_ID",nullable = false)
    private House house;

    public WishListAndHouse (User user, House house) {
        this.user = user;
        this.house = house;
    }
}
