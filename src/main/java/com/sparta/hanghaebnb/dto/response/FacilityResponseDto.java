package com.sparta.hanghaebnb.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FacilityResponseDto {
    private String type;

    public FacilityResponseDto(String type) {
        this.type = type;
    }
}
