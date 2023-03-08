package com.sparta.hanghaebnb.dto.response;

import com.sparta.hanghaebnb.entity.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ReviewResponseDto {

    private Long id;
    private String review;
    private String userName;
    private String createdAt;

    @Builder
    private ReviewResponseDto(Long id, String review, String userName, String createdAt) {
        this.id = id;
        this.review = review;
        this.userName = userName;
        this.createdAt = createdAt;
    }

    public static ReviewResponseDto from(Review review){
        return ReviewResponseDto.builder()
                .id(review.getId())
                .review(review.getReview())
                .userName(review.getUser().getUsername())
                .createdAt(covertString(review.getCreatedAt()))
                .build();

    }

    private static String covertString(LocalDateTime createdAt) {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy년 MM월"));
    }
}
