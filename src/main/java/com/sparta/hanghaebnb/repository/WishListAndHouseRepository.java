package com.sparta.hanghaebnb.repository;

import com.sparta.hanghaebnb.entity.House;
import com.sparta.hanghaebnb.entity.WishList;
import com.sparta.hanghaebnb.entity.WishListAndHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishListAndHouseRepository extends JpaRepository<WishListAndHouse,Long> {
//    Optional<WishListAndHouse> findWishListAndHouse (WishList wishList, House house);
    Optional<WishListAndHouse> findByWishListAndHouse (WishList wishList, House house);
}
