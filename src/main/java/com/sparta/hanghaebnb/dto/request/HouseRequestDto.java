package com.sparta.hanghaebnb.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@ToString
public class HouseRequestDto {

    @NotNull @NotBlank
    private String title;
    @Min(10000)
    private int price;
    @NotNull @NotBlank
    private String explaination;
    @NotNull @NotBlank
    private String location;
    @Min(0) @Max(50)
    private int maxPeople;
    private String houseCase;
    @Min(0) @Max(4)
    private int bedRoom;
    @Min(0) @Max(4)
    private int bathRoom;
    @NotNull
    private String[] facilities ;

    private MultipartFile file;

}
