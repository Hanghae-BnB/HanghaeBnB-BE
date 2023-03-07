package com.sparta.hanghaebnb.controller;

import com.sparta.hanghaebnb.dto.request.HouseRequestDto;
import com.sparta.hanghaebnb.dto.response.HouseResponseDto;
import com.sparta.hanghaebnb.dto.response.MessageResponseDto;
import com.sparta.hanghaebnb.response.ApiDocumentResponse;
import com.sparta.hanghaebnb.security.UserDetailsImpl;
import com.sparta.hanghaebnb.service.HouseService;
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

    /**
     * 여행지 등록 관련 Controller
     */
    @PostMapping("/houses")
    public MessageResponseDto createHouse(@Valid @ModelAttribute HouseRequestDto houseRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, BindingResult result) throws IOException {
        log.info("houseRequestDto = {}" , houseRequestDto);
        return houseService.join(houseRequestDto,userDetails);
    }

    /**
     * 모든 여행지 조회 Controller
     */
    @GetMapping("/houses")
    public List<HouseResponseDto> houses(){
        return houseService.findAllHouse();
    }


    /**
     * 여행지 상세보기 Controller
     */
    @GetMapping("/houses/{house-id}")
    public HouseResponseDto house(@PathVariable(name="house-id") Long houseId){
        return houseService.findHouse(houseId);
    }

    /**
     * 여행지 수정 Controller
     */
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
