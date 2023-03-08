package com.sparta.hanghaebnb.repository;

import com.sparta.hanghaebnb.entity.Facility;
import com.sparta.hanghaebnb.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface FacilityRepository extends JpaRepository<Facility,Long> {
    void deleteAllByHouseId(Long house);
    Set<Facility> findAllByHouse(House house);
}
