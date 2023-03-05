package com.sparta.hanghaebnb.service;

import com.sparta.hanghaebnb.dto.HouseRequestDto;
import com.sparta.hanghaebnb.dto.HouseResponseDto;
import com.sparta.hanghaebnb.dto.MessageResponseDto;
import com.sparta.hanghaebnb.entity.Facility;
import com.sparta.hanghaebnb.entity.House;
import com.sparta.hanghaebnb.repository.FacilityRepository;
import com.sparta.hanghaebnb.repository.HouseRepository;
import com.sparta.hanghaebnb.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HouseService {

    private final HouseRepository houseRepository;
    private final FacilityRepository facilityRepository;
    private final S3Uploader s3Uploader;

    /**
     * 게시글 작성 기능
     */
    public MessageResponseDto join(HouseRequestDto houseRequestDto, UserDetailsImpl userDetails) throws IOException {
        //S3에 이미지 저장하고 URL 받아오기
        String imgUrl = s3Uploader.upload(houseRequestDto.getFile());

        // House Entity 생성
        House newHouse = House.of(houseRequestDto, userDetails.getUser(),imgUrl);
        // 편의시설 Entity 생성
        List<Facility> facilities = Arrays.stream(houseRequestDto.getFacilities()).map(f -> new Facility(f, newHouse)).collect(Collectors.toList());
        newHouse.addFacilities(facilities);
        houseRepository.save(newHouse);
        return new MessageResponseDto("성공", HttpStatus.OK);
    }

    /**
     * 게시글 전체 조회 기능 (추가 진행 예정)
     */
    @Transactional(readOnly = true)
    public List<HouseResponseDto> findAllHouse() {
        List<House> houses = houseRepository.findAllByOrderByCreatedAtDesc();
        return houses.stream().map(h -> HouseResponseDto.from(h,(int)(Math.random()*100),(int)(Math.random()*100))).collect(Collectors.toList());
    }

    /**
     * 해당 게시글 조회 기능(추가 진행 예정)
     */
    @Transactional(readOnly = true)
    public HouseResponseDto findHouse(Long houseId) {
        House findHouse = houseRepository.findById(houseId).orElseThrow(
                () -> new IllegalStateException("해당 게시글이 존재하지 않습니다")
        );
        return HouseResponseDto.from(findHouse,10,10);
    }

    /**
     * 해당 게시글 수정 기능(추가 진행 예정)
     */
    public MessageResponseDto update(Long houseId, HouseRequestDto houseRequestDto) {
        House findHouse = houseRepository.findById(houseId).orElseThrow(
                () -> new IllegalStateException("해당 게시글이 존재하지 않습니다")
        );
        facilityRepository.deleteAllByHouseId(houseId);
        List<Facility> facilities = Arrays.stream(houseRequestDto.getFacilities()).map(f -> new Facility(f, findHouse)).collect(Collectors.toList());

        findHouse.update(houseRequestDto,facilities);
        return new MessageResponseDto("수정완료",HttpStatus.OK);

    }

    /**
     * 해당 게시글 삭제 기능(추가 진행 예정)
     */
    public MessageResponseDto remove(Long houseId) {
        House findHouse = houseRepository.findById(houseId).orElseThrow(
                () -> new IllegalStateException("해당 게시글이 존재하지 않습니다")
        );
        
        houseRepository.delete(findHouse);
        return new MessageResponseDto("삭제완료",HttpStatus.OK);
    }

    /**
     * 카테고리별 여행지 조회
     */
    public List<HouseResponseDto> categoryHouses(String category) {
        List<House> findHouses = houseRepository.findAllByCategoryOrderByCreatedAtDesc(category);
        return findHouses.stream().map(h -> HouseResponseDto.from(h,(int)(Math.random()*100),(int)(Math.random()*100))).collect(Collectors.toList());
    }

    /**
     * 검색 기능으로 여행지 조회
     */
    public List<HouseResponseDto> keywordHouse(String keyword) {
        List<House> findHouses = houseRepository.findAllByTitleContainsOrderByCreatedAtDesc(keyword);
        return findHouses.stream().map(h -> HouseResponseDto.from(h,(int)(Math.random()*100),(int)(Math.random()*100))).collect(Collectors.toList());
    }
}
