package com.locShop.repository;

import com.locShop.model.CartEntity;
import com.locShop.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<CartEntity,Long> {

    CartEntity findByUserId(Long userId);

}
