package com.sparta.hanghaebnb.repository;

import com.sparta.hanghaebnb.entity.User;
import com.sparta.hanghaebnb.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList,Long>  {
    Optional<WishList> findByUser(User user);
}
