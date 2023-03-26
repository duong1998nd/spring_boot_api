package com.locShop.repository;

import com.locShop.model.CartItemEntity;
import com.locShop.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import com.locShop.model.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

    List<ProductEntity> findAllByCategory_id(Long category_id);

    Optional<ImageData> findByName(String filename);
}
