package com.sparta.hanghaebnb.repository;

import com.sparta.hanghaebnb.entity.House;
import com.sparta.hanghaebnb.entity.User;
import com.sparta.hanghaebnb.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish,Long> {
//    Optional<WishListAndHouse> findWishListAndHouse (WishList wishList, House house);
    Optional<Wish> findByUserAndHouse (User user, House house);
    Optional<Wish> findByUser (User user);

}
