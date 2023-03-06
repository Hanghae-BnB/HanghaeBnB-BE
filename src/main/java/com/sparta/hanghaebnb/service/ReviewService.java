package com.sparta.hanghaebnb.service;

import com.sparta.hanghaebnb.dto.response.MessageResponseDto;
import com.sparta.hanghaebnb.dto.request.ReviewRequestDto;
import com.sparta.hanghaebnb.entity.House;
import com.sparta.hanghaebnb.entity.Review;
import com.sparta.hanghaebnb.entity.User;
import com.sparta.hanghaebnb.exception.CustomException;
import com.sparta.hanghaebnb.exception.ErrorCode;
import com.sparta.hanghaebnb.repository.HouseRepository;
import com.sparta.hanghaebnb.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final HouseRepository houseRepository;
    private final ReviewRepository reviewRepository;

    // 리뷰 생성
    public MessageResponseDto createReview(Long id, ReviewRequestDto reviewRequestDto, User user) {
        // 해당 게시글이 있는지 확인
        Optional<House> house = houseRepository.findById(id);
        if (house.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_HOUSE);
        } else {
            Review review = new Review(reviewRequestDto, user, house.get());
            reviewRepository.save(review);
            return new MessageResponseDto("리뷰 작성 성공!", HttpStatus.OK);
        }

    }
}
