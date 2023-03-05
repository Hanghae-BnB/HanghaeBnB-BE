package com.sparta.hanghaebnb.repository;

import com.sparta.hanghaebnb.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseRepository extends JpaRepository<House,Long> {

    //생성일 내림차순 전체 조회
    List<House> findAllByOrderByCreatedAtDesc();
    //카테고리에 따른 전체 여행지 검색
    List<House> findAllByCategoryOrderByCreatedAtDesc(String category);
    //제목에 키워드가 포함되어 있는 전체 여행지 검색
    List<House> findAllByTitleContainsOrderByCreatedAtDesc(String category);
}
