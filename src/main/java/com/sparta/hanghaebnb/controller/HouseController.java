package com.sparta.hanghaebnb.controller;

import com.sparta.hanghaebnb.dto.request.HouseRequestDto;
import com.sparta.hanghaebnb.dto.response.HouseDetailResponseDto;
import com.sparta.hanghaebnb.dto.response.HouseResponseDto;
import com.sparta.hanghaebnb.dto.response.MessageResponseDto;
import com.sparta.hanghaebnb.response.ApiDocumentResponse;
import com.sparta.hanghaebnb.security.UserDetailsImpl;
import com.sparta.hanghaebnb.service.HouseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@ApiDocumentResponse
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class HouseController {

    private final HouseService houseService;
    
    @Operation(summary = "숙박지 등록 요청", description = "숙박지가 추가됩니다.", tags = {"Houser"})
    @PostMapping("/houses")
    public MessageResponseDto createHouse(@Valid @ModelAttribute HouseRequestDto houseRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("houseRequestDto = {}" , houseRequestDto);
        return houseService.join(houseRequestDto,userDetails);
    }

    @GetMapping("/houses")
    public List<HouseResponseDto> houses(){
        return houseService.findAllHouse();
    }


    @Operation(summary = "숙박지 상세 페이지 요청", description = "상세페이지 정보 조회", tags = {"House"})
    @GetMapping("/houses/{house-id}")
    public HouseDetailResponseDto house(@PathVariable(name="house-id") Long houseId){
        return houseService.findHouse(houseId);
    }

    @Operation(summary = "숙박지 수정 요청", description = "해당 숙박지가 수정됩니다", tags = {"House"})
    @PutMapping("/houses/{house-id}")
    public MessageResponseDto updateHouse(@PathVariable(name = "house-id") Long houseId, @ModelAttribute HouseRequestDto houseRequestDto){
        return houseService.update(houseId, houseRequestDto);
    }

    /**
     * 여행지 삭제 Controller
     */
    @DeleteMapping("/houses/{house-id}")
    public MessageResponseDto removeHouse(@PathVariable(name="house-id") Long houseId){
        return houseService.remove(houseId);
    }

    /**
     * 여행지 카테고리별 조회 Controller
     */
    @GetMapping("/houses/house-case/{house-case}")
    public List<HouseResponseDto> categoryHouse(@PathVariable(name="house-case") String houseCase){
        return houseService.categoryHouses(houseCase);
    }

    /**
     * 검색 기능으로 여행지 조회 Controller
     */
    @GetMapping("/house")
    public List<HouseResponseDto> keywordHouse(@RequestParam("keyword") String keyword){
        return houseService.keywordHouse(keyword);
    }

}
