package com.sparta.hanghaebnb.service;

import com.sparta.hanghaebnb.dto.HouseRequestDto;
import com.sparta.hanghaebnb.dto.MessageResponseDto;
import com.sparta.hanghaebnb.entity.Facility;
import com.sparta.hanghaebnb.entity.House;
import com.sparta.hanghaebnb.repository.HouseRepository;
import com.sparta.hanghaebnb.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HouseService {

    private final HouseRepository houseRepository;

    /**
     * 게시글 작성 기능
     */
    public MessageResponseDto join(HouseRequestDto houseRequestDto, UserDetailsImpl userDetails) {
        // House Entity 생성
        House newHouse = House.of(houseRequestDto, userDetails.getUser());
        List<Facility> facilities = Arrays.stream(houseRequestDto.getFacilities()).map(f -> new Facility(f, newHouse)).collect(Collectors.toList());
        newHouse.addFacilities(facilities);
        houseRepository.save(newHouse);
        return new MessageResponseDto("성공", HttpStatus.OK);
    }
}
