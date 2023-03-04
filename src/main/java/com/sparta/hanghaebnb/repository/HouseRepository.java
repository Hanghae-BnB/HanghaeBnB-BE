package com.sparta.hanghaebnb.repository;

import com.sparta.hanghaebnb.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House,Long> {
}
