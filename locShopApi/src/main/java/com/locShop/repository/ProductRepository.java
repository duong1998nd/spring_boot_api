package com.locShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.locShop.model.ProductEntity;


public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

}
