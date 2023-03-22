package com.locShop.repository;

import com.locShop.model.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import com.locShop.model.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository extends JpaRepository<ProductEntity, Long>{


}
