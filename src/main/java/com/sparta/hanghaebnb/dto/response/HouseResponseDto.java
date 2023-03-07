package com.sparta.hanghaebnb.dto.response;

import com.sparta.hanghaebnb.entity.House;
import lombok.Builder;
import lombok.Getter;

import java.text.DecimalFormat;

@Getter
public class HouseResponseDto {

    private Long id;
    private String userNickname;
    private String title;
    private String distance;
    private String location;
    private String price;
    private String imageUrl;
    private int likesNum;
    private int reviewNum;

    @Builder
    private HouseResponseDto(Long id, String userNickname, String title, String distance, String location, String price, String imageUrl, int likesNum, int reviewNum) {
        this.id = id;
        this.userNickname = userNickname;
        this.title = title;
        this.distance = distance;
        this.location = location;
        this.price = price;
        this.imageUrl = imageUrl;
        this.likesNum = likesNum;
        this.reviewNum = reviewNum;
    }




    public static HouseResponseDto of(House house,int likesNum , int reviewNum){
        DecimalFormat format = new DecimalFormat("###,###");
        return HouseResponseDto.builder()
                .id(house.getId())
                .userNickname(house.getUser().getUsername())
                .title(house.getTitle())
                .location(house.getLocation())
                .price(format.format(house.getPrice()))
                .distance( format.format( (int) ( 1000 + Math.random() * 9000 ) ) )
                .imageUrl(house.getImgUrl())
                .likesNum(likesNum)
                .reviewNum(reviewNum)
                .build();
    }
}
