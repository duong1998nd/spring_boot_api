package com.locShop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.locShop.model.ProductEntity;
import com.locShop.repository.ProductRepository;

@Service
public class ProductService{
	
	@Autowired
	ProductRepository productRepository;

	public ProductEntity save(ProductEntity entity) {
		ProductEntity pro = productRepository.save(entity);
		return pro!=null?entity:null;
	}

	public Optional<ProductEntity> findById(Long id) {
		Optional<ProductEntity> product = productRepository.findById(id);
        if(!product.isPresent()) {
            return null;
        }
		return product;
	}

	public List<ProductEntity> findAll() {
		return productRepository.findAll();
	}
	
}
