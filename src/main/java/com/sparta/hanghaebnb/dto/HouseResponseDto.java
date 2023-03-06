package com.sparta.hanghaebnb.dto;

import com.sparta.hanghaebnb.entity.House;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HouseResponseDto {

    // 하우스 ID
    private Long id;
    // 호스트명
    private String userNickname;
    // 글 제목
    private String title;
    // 하우스 위치
    private String location;
    // 숙박료
    private int price;
    // 하우스이미지
    private String imageUrl;
    //좋아요 수
    private int likesNum;
    //리뷰 수
    private int reviewNum;

    @Builder
    public HouseResponseDto(Long id, String userNickname, String title, String location, int price, String imageUrl, int likesNum, int reviewNum) {
        this.id = id;
        this.userNickname = userNickname;
        this.title = title;
        this.location = location;
        this.price = price;
        this.imageUrl = imageUrl;
        this.likesNum = likesNum;
        this.reviewNum = reviewNum;
    }

    public static HouseResponseDto from(House house, int likesNum , int reviewNum){
        return HouseResponseDto.builder()
                .id(house.getId())
                .userNickname(house.getUser().getUsername())
                .title(house.getTitle())
                .location(house.getLocation())
                .price(house.getPrice())
                .imageUrl(house.getImgUrl())
                .likesNum(likesNum)
                .reviewNum(reviewNum)
                .build();
    }
}
