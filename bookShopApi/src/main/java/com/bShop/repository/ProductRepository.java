package com.bShop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bShop.model.ImageData;
import com.bShop.model.ProductEntity;


public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

    List<ProductEntity> findAllByCategory_id(Long category_id);

    Optional<ImageData> findByName(String filename);
}
