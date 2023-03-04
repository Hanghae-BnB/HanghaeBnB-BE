package com.sparta.hanghaebnb.dto;

import com.sparta.hanghaebnb.entity.House;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HouseResponseDto {

    private Long id;
    private String userNuckname;
    private String title;
    private String imageUrl;
    private int likesNum;
    private int commentsNum;

    @Builder
    private HouseResponseDto(Long id, String userNuckname, String title, String imageUrl, int likesNum, int commentsNum) {
        this.id = id;
        this.userNuckname = userNuckname;
        this.title = title;
        this.imageUrl = imageUrl;
        this.likesNum = likesNum;
        this.commentsNum = commentsNum;
    }

    public static HouseResponseDto from(House house, int likesNum , int commentsNum){
        return HouseResponseDto.builder()
                .id(house.getId())
                .userNuckname(house.getUser().getUsername())
                .title(house.getTitle())
                .imageUrl(house.getImgUrl())
                .likesNum(likesNum)
                .commentsNum(commentsNum)
                .build();
    }
}
