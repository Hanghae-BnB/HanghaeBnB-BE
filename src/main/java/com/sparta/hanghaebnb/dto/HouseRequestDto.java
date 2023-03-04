package com.sparta.hanghaebnb.dto;

import com.sparta.hanghaebnb.entity.Facility;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class HouseRequestDto {

    private String title;
    private String detail;
    private int price;
    private String location;
    private String category;
    private int maxPeople;
    private String houseCase;
    private int bedRoom;
    private int bathRoom;
    private String[] facilities ;
    private MultipartFile file;

}
