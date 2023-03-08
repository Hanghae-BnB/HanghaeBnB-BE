package com.sparta.hanghaebnb.entity;

import com.sparta.hanghaebnb.dto.request.HouseRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private String explaination;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String houseCase;

    @Column(nullable = false)
    private int maxPeople;

    @Column(nullable = false)
    private int bedRoom;
    @Column(nullable = false)
    private int bedNum;

    @Column(nullable = false)
    private int bathRoom;
    @Column(nullable = false)
    private String imgUrl;
    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private Set<Facility> facilities = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID",nullable = false)
    private User user;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Wish> wishList= new ArrayList<>();

    @Builder
    private House(String title, int price, String location, String explaination, String imgUrl, int maxPeople, int bedNum, int bedRoom,int bathRoom, String houseCase,User user) {
        this.title = title;
        this.price = price;
        this.location = location;
        this.explaination = explaination;
        this.imgUrl = imgUrl;
        this.maxPeople = maxPeople;
        this.bedNum = bedNum;
        this.bedRoom = bedRoom;
        this.bathRoom = bathRoom;
        this.houseCase = houseCase;
        this.user = user;
    }
    public static House of(HouseRequestDto houseRequestDto,User user,String imgUrl){
        return House.builder()
                .title(houseRequestDto.getTitle())
                .price(houseRequestDto.getPrice())
                .location(houseRequestDto.getLocation())
                .explaination(houseRequestDto.getExplaination())
                .imgUrl(imgUrl)
                .maxPeople(houseRequestDto.getMaxPeople())
                .bedNum(houseRequestDto.getBedNum())
                .bedRoom(houseRequestDto.getBedRoom())
                .bathRoom(houseRequestDto.getBathRoom())
                .houseCase(houseRequestDto.getHouseCase())
                .user(user)
                .build();
    }

    public void update(HouseRequestDto houseRequestDto,String imgUrl) {
        this.title = houseRequestDto.getTitle();
        this.price =houseRequestDto.getPrice();
        this.location = houseRequestDto.getLocation();
        this.explaination = houseRequestDto.getLocation();
        this.imgUrl = houseRequestDto.getFile().getName();
        this.maxPeople = houseRequestDto.getMaxPeople();
        this.bedNum = houseRequestDto.getBedRoom();
        this.bedRoom = houseRequestDto.getBedRoom();
        this.bathRoom = houseRequestDto.getBathRoom();
        this.houseCase = houseRequestDto.getHouseCase();
        this.imgUrl = imgUrl;
    }
}
