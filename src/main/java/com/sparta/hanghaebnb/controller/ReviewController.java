package com.sparta.hanghaebnb.controller;

import com.sparta.hanghaebnb.dto.response.MessageResponseDto;
import com.sparta.hanghaebnb.dto.request.ReviewRequestDto;
import com.sparta.hanghaebnb.response.ApiDocumentResponse;
import com.sparta.hanghaebnb.security.UserDetailsImpl;
import com.sparta.hanghaebnb.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
@ApiDocumentResponse
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping("/houses/{houseId}/reviews")  // board_id
    public MessageResponseDto createReview(@PathVariable Long houseId, @RequestBody ReviewRequestDto reviewRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return reviewService.createReview(houseId, reviewRequestDto, userDetails.getUser());
    }
    // 리뷰 작성
    @DeleteMapping("/houses/{houseId}/reviews")  // board_id
    public MessageResponseDto deleteReview(@PathVariable Long houseId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return reviewService.deleteReview(houseId, userDetails.getUser());
    }



}
