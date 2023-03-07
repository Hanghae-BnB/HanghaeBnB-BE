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

    // 리뷰 작성
    public MessageResponseDto createReview(Long id, ReviewRequestDto reviewRequestDto, User user) {
        // 해당 숙소가 있는지 확인
        Optional<House> house = houseRepository.findById(id);
        if (house.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_HOUSE);
        }

        // 이미 리뷰가 작성되었는지 확인
        Optional<Review> compare = reviewRepository.findByUserAndHouse(user, house.get());
        if(compare.isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_EXISTS_REVIEW);
        }

        Review review = new Review(reviewRequestDto, user, house.get());
        reviewRepository.save(review);
        return new MessageResponseDto("리뷰 작성 성공!", HttpStatus.OK);
    }

    // 리뷰 삭제
    public MessageResponseDto deleteReview(Long id, User user) {
        // 해당 숙소가 있는지 확인
        Optional<House> house = houseRepository.findById(id);
        if (house.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_HOUSE);
        }

        // 이미 리뷰가 작성되었는지 확인
        Optional<Review> compare = reviewRepository.findByUserAndHouse(user, house.get());
        if(compare.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_REVIEW);
        }

        reviewRepository.delete(compare.get());
        return new MessageResponseDto("리뷰 삭제 성공!", HttpStatus.OK);
    }

}
