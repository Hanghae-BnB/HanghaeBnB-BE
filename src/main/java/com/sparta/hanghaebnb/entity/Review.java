package com.sparta.hanghaebnb.entity;

import com.sparta.hanghaebnb.dto.request.ReviewRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String review;

    @Column(nullable = false)
    private float star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOUSE_ID",nullable = false)
    private House house;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID",nullable = false)
    private User user;

    public Review(ReviewRequestDto reviewRequestDto, User user, House house){
        this.review = reviewRequestDto.getReview();
        this.star = reviewRequestDto.getStar();
        this.user = user;
        this.house = house;
    }



}
