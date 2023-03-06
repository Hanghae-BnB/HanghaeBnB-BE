package com.sparta.hanghaebnb.repository;

import com.sparta.hanghaebnb.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseRepository extends JpaRepository<House,Long> {

    List<House> findAllByOrderByCreatedAtDesc();
    List<House> findAllByCategoryOrderByCreatedAtDesc(String category);
}
