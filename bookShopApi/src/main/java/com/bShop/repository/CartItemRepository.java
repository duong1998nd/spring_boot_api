package com.bShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bShop.model.CartItemEntity;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long>{

    CartItemEntity findByProductId(Long proId);

    CartItemEntity findByCartId(Long cartId);

    CartItemEntity findByCartIdAndProductId(Long cartId, Long proId);

    Iterable<CartItemEntity> findAllByCartId(Long id);
}
