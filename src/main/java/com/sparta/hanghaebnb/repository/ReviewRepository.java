package com.sparta.hanghaebnb.repository;

import com.sparta.hanghaebnb.entity.Facility;
import com.sparta.hanghaebnb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

}
