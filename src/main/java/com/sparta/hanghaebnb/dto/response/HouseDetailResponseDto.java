package com.sparta.hanghaebnb.dto.response;

import com.sparta.hanghaebnb.entity.Facility;
import com.sparta.hanghaebnb.entity.House;
import lombok.Builder;
import lombok.Getter;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class HouseDetailResponseDto {

    private Long id;

    private String title;

    private String userName;

    private String imageUrl;

    private String explaination;

    private String price;

    private String location;

    private String distance;

    private String houseCase;

    private int maxPeople;

    private int bedRoom;

    private int bedNum;

    private int bathRoom;

    private LocalDateTime createdAt;

    List<FacilityResponseDto> facilities = new ArrayList<>();

    List<ReviewResponseDto> reviews = new ArrayList<>();

    private double starNum ;

    private int reviewNum ;

    @Builder
    private HouseDetailResponseDto(
           Long id, String title, String userName, String imageUrl, String explaination,
           String price, String location, String distance, String houseCase,
           int bedNum, int bathRoom, double starNum, int reviewNum , int bedRoom,int maxPeople,
           LocalDateTime createdAt, List<FacilityResponseDto> facilities, List<ReviewResponseDto> reviews
                                   )
    {
        this.id = id;
        this.title = title;
        this.userName = userName;
        this.imageUrl = imageUrl;
        this.explaination = explaination;
        this.price = price;
        this.location = location;
        this.distance = distance;
        this.houseCase = houseCase;
        this.maxPeople = maxPeople;
        this.bedRoom = bedRoom;
        this.bedNum = bedNum;
        this.bathRoom = bathRoom;
        this.createdAt = createdAt;
        this.facilities = facilities;
        this.reviews = reviews;
        this.starNum = starNum;
        this.reviewNum = reviewNum;
    }

    public static HouseDetailResponseDto of(House house, List<ReviewResponseDto> reviews,List<FacilityResponseDto> facilities,double starNum){
        DecimalFormat format = new DecimalFormat("###,###");
        return HouseDetailResponseDto.builder()
                .id(house.getId())
                .title(house.getTitle())
                .userName(house.getUser().getUsername())
                .imageUrl(house.getImgUrl())
                .explaination(house.getExplaination())
                .price(format.format(house.getPrice()))
                .location(house.getLocation())
                .distance( format.format( (int) ( 1000 + Math.random() * 9000 ) ) )
                .houseCase(house.getHouseCase())
                .maxPeople(house.getMaxPeople())
                .bedRoom(house.getBedRoom())
                .bedNum(house.getBedNum())
                .bathRoom(house.getBathRoom())
                .createdAt(house.getCreatedAt())
                .facilities(facilities)
                .reviews(reviews)
                .starNum(starNum)
                .reviewNum(reviews.size())
                .build();
    }
}
