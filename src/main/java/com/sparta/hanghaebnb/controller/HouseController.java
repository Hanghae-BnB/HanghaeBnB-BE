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
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    
    @Operation(summary = "숙박지 등록 요청", description = "숙박지가 추가", tags = {"House"})
    @PostMapping("/houses")
    public MessageResponseDto createHouse(@Valid @ModelAttribute HouseRequestDto houseRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("houseRequestDto = {}" , houseRequestDto);
        return houseService.join(houseRequestDto,userDetails);
    }

    @Operation(summary = "숙박지 전체 조회 요청", description = "요청 페이지 당 size만큼 등록일 내림차순 조회", tags = {"House"})
    @GetMapping("/houses")
    public List<HouseResponseDto> houses(@RequestParam("page") int page, @RequestParam("size") int size){
        return houseService.findAllHouse(page-1, size);
    }

    @Operation(summary = "숙박지 상세 페이지 요청", description = "상세페이지 정보 조회", tags = {"House"})
    @GetMapping("/houses/{house-id}")
    public HouseDetailResponseDto house(@PathVariable(name="house-id") Long houseId){
        return houseService.findHouse(houseId);
    }

    @Operation(summary = "숙박지 수정 요청", description = "해당 숙박지 수정", tags = {"House"})
    @PutMapping("/houses/{house-id}")
    public MessageResponseDto updateHouse(@PathVariable(name = "house-id") Long houseId, @ModelAttribute HouseRequestDto houseRequestDto){
        return houseService.update(houseId, houseRequestDto);
    }

    @Operation(summary = "숙박지 삭제 요청", description = "해당 숙박지 삭제", tags = {"House"})
    @DeleteMapping("/houses/{house-id}")
    public MessageResponseDto removeHouse(@PathVariable(name="house-id") Long houseId){
        return houseService.remove(houseId);
    }
    @Operation(summary = "숙박 형태에 따른 요청", description = "숙박형태에 따라 요청 페이지 당 size만큼  조회", tags = {"House"})
    @GetMapping("/houses/house-case/{house-case}")
    public List<HouseResponseDto> categoryHouse(@PathVariable(name="house-case") String houseCase,
                                                @RequestParam("page") int page,
                                                @RequestParam("size") int size){
        return houseService.categoryHouses(houseCase, page-1, size);
    }

    @Operation(summary = "검색어에 따른 요청", description = "검색어에 따라 요청 페이지 당 size만큼  조회", tags = {"House"})
    @GetMapping("/house")
    public List<HouseResponseDto> keywordHouse(@RequestParam("keyword") String keyword,
                                               @RequestParam("page") int page,
                                               @RequestParam("size") int size){
        return houseService.keywordHouse(keyword,page-1,size);

    }

}
