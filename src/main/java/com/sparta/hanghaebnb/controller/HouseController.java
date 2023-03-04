package com.sparta.hanghaebnb.controller;

import com.sparta.hanghaebnb.dto.HouseRequestDto;
import com.sparta.hanghaebnb.dto.HouseResponseDto;
import com.sparta.hanghaebnb.dto.MessageResponseDto;
import com.sparta.hanghaebnb.security.UserDetailsImpl;
import com.sparta.hanghaebnb.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public MessageResponseDto createHouse(@ModelAttribute HouseRequestDto houseRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
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
    @GetMapping("/houses/{houseId}")
    public HouseResponseDto house(@PathVariable Long houseId){
        return houseService.findHouse(houseId);
    }

    /**
     * 여행지 수정 Controller
     */
    @PutMapping("/houses/{houseId}")
    public MessageResponseDto updateHouse(@PathVariable Long houseId, @ModelAttribute HouseRequestDto houseRequestDto){
        return houseService.update(houseId, houseRequestDto);
    }
}
