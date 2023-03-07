package com.sparta.hanghaebnb.controller;

import com.sparta.hanghaebnb.dto.response.MessageResponseDto;
import com.sparta.hanghaebnb.response.ApiDocumentResponse;
import com.sparta.hanghaebnb.security.UserDetailsImpl;
import com.sparta.hanghaebnb.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@ApiDocumentResponse
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WishController {

    private final WishService wishListService;

    // wishlist에 숙소 추가하기
    @PostMapping("/houses/{house-id}/wish")
    public MessageResponseDto addWish(@PathVariable("house-id") Long houseId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return wishListService.addWish(houseId, userDetails.getUser());
    }

    @DeleteMapping ("/houses/{house-id}/wish")
    public MessageResponseDto deleteWish(@PathVariable("house-id") Long houseId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return wishListService.deleteWish(houseId, userDetails.getUser());
    }

}
