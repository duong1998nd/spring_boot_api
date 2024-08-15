package com.bShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bShop.model.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity,Long> {

    CartEntity findByUserId(Long userId);

}
