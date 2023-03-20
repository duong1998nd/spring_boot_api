package com.locShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locShop.model.CartItemEntity;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long>{

    CartItemEntity findByProductId(Long proId);

    CartItemEntity findByCartId(Long cartId);

    CartItemEntity findByCartIdAndProductId(Long cartId, Long proId);
}
