package com.sparta.hanghaebnb.service;

import com.sparta.hanghaebnb.dto.response.MessageResponseDto;
import com.sparta.hanghaebnb.entity.House;
import com.sparta.hanghaebnb.entity.User;
import com.sparta.hanghaebnb.entity.Wish;
import com.sparta.hanghaebnb.exception.CustomException;
import com.sparta.hanghaebnb.exception.ErrorCode;
import com.sparta.hanghaebnb.repository.HouseRepository;
import com.sparta.hanghaebnb.repository.UserRepository;
import com.sparta.hanghaebnb.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishService {
    private final HouseRepository houseRepository;
    private final WishRepository wishRepository;

    @Transactional
    public MessageResponseDto addWish(Long id, User user) {
        // 위시리스트에 넣으려는 숙소가 없는 경우
        Optional<House> house = houseRepository.findById(id);
        if (house.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_HOUSE);
        }

        // 이미 위시리스트에 있는 경우
        Optional<Wish> compare = wishRepository.findByUserAndHouse(user, house.get());
        if (compare.isPresent()) {
            throw new CustomException(ErrorCode.ALREADY_EXISTS_WISHLIST);
        }

        wishRepository.save(Wish.of(user, house.get()));
        return new MessageResponseDto("위시리스트 추가 성공!", HttpStatus.OK);

    }

    @Transactional
    public MessageResponseDto deleteWish(Long id, User user) {

        // 위시리스트에 넣으려는 숙소가 없는 경우
        Optional<House> house = houseRepository.findById(id);
        if (house.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_HOUSE);
        }

        // 이미 위시리스트가 삭제된 경우
        Optional<Wish> compare = wishRepository.findByUserAndHouse(user, house.get());
        if (compare.isEmpty()) {
            throw new CustomException(ErrorCode.ALREADY_DELETED_WISHLIST);
        }

        wishRepository.delete(compare.get());
        return new MessageResponseDto("위시리스트 삭제 성공!", HttpStatus.OK);
    }

}
