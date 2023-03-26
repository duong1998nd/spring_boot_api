package com.locShop.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.locShop.model.CartItemEntity;
import com.locShop.model.CategoryEntity;
import com.locShop.model.ImageData;
import com.locShop.repository.CategoryRepository;
import com.locShop.upload.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.locShop.model.ProductEntity;
import com.locShop.repository.ProductRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService{
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public ProductEntity save(ProductEntity entity) {
		if(entity.getId()!=null){
			ProductEntity pro = productRepository.findById(entity.getId()).orElse(null);
			if(pro!=null){
				CategoryEntity category = categoryRepository.findById(entity.getCategory_id()).orElse(null);
				if(category!=null){
					pro.setName(entity.getName());
					pro.setImage(entity.getImage());
					pro.setAuthor(entity.getAuthor());
					pro.setDesciption(entity.getDesciption());
					pro.setPrice(entity.getPrice());
					pro.setSale_price(entity.getSale_price());
					pro.setCategory_id(category);
					return productRepository.save(pro);
				}
			}
		}
		return productRepository.save(entity);
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

	public List<ProductEntity> findAllByCategoryId(Long categoryId){
		return productRepository.findAllByCategory_id(categoryId);
	}

	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
}
