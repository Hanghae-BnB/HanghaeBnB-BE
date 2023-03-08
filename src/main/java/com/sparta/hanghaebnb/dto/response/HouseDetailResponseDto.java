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

    private int bedRoom;

    private int bedNum;

    private int bathNum;

    private LocalDateTime createdAt;

    List<FacilityResponseDto> facilities = new ArrayList<>();

    List<ReviewResponseDto> reviews = new ArrayList<>();

    private double starNum ;

    private int reviewNum ;

    @Builder
    private HouseDetailResponseDto(
           Long id, String title, String userName, String imageUrl, String explaination,
           String price, String location, String distance, String houseCase,
           int bedNum, int bathNum, double starNum, int reviewNum , int bedRoom,
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
        this.bedRoom = bedRoom;
        this.bedNum = bedNum;
        this.bathNum = bathNum;
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
                .bedRoom(house.getBedRoom())
                .bedNum(house.getBedNum())
                .bathNum(house.getBathNum())
                .createdAt(house.getCreatedAt())
                .facilities(facilities)
                .reviews(reviews)
                .starNum(starNum)
                .reviewNum(reviews.size())
                .build();
    }
}
