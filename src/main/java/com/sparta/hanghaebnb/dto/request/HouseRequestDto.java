package com.sparta.hanghaebnb.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class HouseRequestDto {

    private String title;
    private int price;
    private String explaination;
    private String location;
    private int maxPeople;
    private String houseCase;
    private int bedRoom;
    private int bathRoom;
    private String[] facilities ;
    private MultipartFile file;

}
