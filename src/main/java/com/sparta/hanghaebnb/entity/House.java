package com.sparta.hanghaebnb.entity;

import com.sparta.hanghaebnb.dto.HouseRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class House extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String explanation;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private int maxNumPeople;

    @Column(nullable = false)
    private int bedNum;

    @Column(nullable = false)
    private int bathNum;

    @Column(nullable = false)
    private String houseCase;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Facility> facilities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID",nullable = false)
    private User user;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WISHLIST_ID")
    private WishList wishList ;

    @Builder
    private House(String title, int price, String location, String explanation, String imgUrl, int maxNumPeople, int bedNum, int bathNum, String houseCase, User user) {
        this.title = title;
        this.price = price;
        this.location = location;
        this.explanation = explanation;
        this.imgUrl = imgUrl;
        this.maxNumPeople = maxNumPeople;
        this.bedNum = bedNum;
        this.bathNum = bathNum;
        this.houseCase = houseCase;
        this.user = user;
    }

    public static House of(HouseRequestDto houseRequestDto,User user){
        return House.builder()
                .title(houseRequestDto.getTitle())
                .price(houseRequestDto.getPrice())
                .location(houseRequestDto.getLocation())
                .explanation(houseRequestDto.getHouseCase())
                .imgUrl(houseRequestDto.getFile().getName())
                .maxNumPeople(houseRequestDto.getMaxPeople())
                .bedNum(houseRequestDto.getBedRoom())
                .bathNum(houseRequestDto.getBathRoom())
                .houseCase(houseRequestDto.getHouseCase())
                .user(user)
                .build();
    }

    //연관관계 매세드
    public void addFacilities(List<Facility> facilities){
        this.facilities = facilities;
    }
}
