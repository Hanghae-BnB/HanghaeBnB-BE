package com.sparta.hanghaebnb.controller;

import com.sparta.hanghaebnb.dto.response.MessageResponseDto;
import com.sparta.hanghaebnb.response.ApiDocumentResponse;
import com.sparta.hanghaebnb.security.UserDetailsImpl;
import com.sparta.hanghaebnb.service.WishService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@ApiDocumentResponse
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WishController {

    private final WishService wishListService;

    @Operation(summary = "해당 숙박지 위시리스트 추가 요청", description = "선택한 숙박지를 위시리스트에 추가", tags = {"wish"})
    @PostMapping("/houses/{house-id}/wish")
    public MessageResponseDto addWish(@PathVariable("house-id") Long houseId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return wishListService.addWish(houseId, userDetails.getUser());
    }

    @Operation(summary = "해당 숙박지 위시리스트 삭제 요청", description = "선택한 숙박지를 위시리스트에 삭제", tags = {"wish"})
    @DeleteMapping ("/houses/{house-id}/wish")
    public MessageResponseDto deleteWish(@PathVariable("house-id") Long houseId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return wishListService.deleteWish(houseId, userDetails.getUser());
    }

}
