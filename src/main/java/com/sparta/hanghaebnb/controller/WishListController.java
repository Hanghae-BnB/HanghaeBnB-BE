package com.sparta.hanghaebnb.controller;

import com.sparta.hanghaebnb.dto.MessageResponseDto;
import com.sparta.hanghaebnb.response.ApiDocumentResponse;
import com.sparta.hanghaebnb.security.UserDetailsImpl;
import com.sparta.hanghaebnb.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@ApiDocumentResponse
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WishListController {

    private final WishListService wishListService;

    // wishlist에 숙소 추가하기
    @PostMapping("/houses/{houseId}/wishList")
    public MessageResponseDto addWishList(@PathVariable Long houseId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return wishListService.addWishList(houseId, userDetails.getUser());
    }

    @DeleteMapping ("/houses/{houseId}/wishList")
    public MessageResponseDto deleteWishList(@PathVariable Long houseId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return wishListService.deleteWishList(houseId, userDetails.getUser());
    }

}
