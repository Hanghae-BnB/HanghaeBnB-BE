package com.sparta.hanghaebnb.repository;

import com.sparta.hanghaebnb.entity.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;


public interface HouseRepository extends JpaRepository<House,Long> {

    //생성일 내림차순 전체 조회
    Page<House> findAllByOrderByCreatedAtDesc(Pageable pageable);
    //카테고리에 따른 전체 여행지 검색
    Page<House> findAllByHouseCaseOrderByCreatedAtDesc(String houseCase, Pageable pageable);
    //제목에 키워드가 포함되어 있는 전체 여행지 검색

    Page<House> findAllByTitleContainsOrderByCreatedAtDesc(String category, Pageable pageable);

}
