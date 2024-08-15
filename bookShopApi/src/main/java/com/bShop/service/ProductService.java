package com.bShop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bShop.model.CategoryEntity;
import com.bShop.model.ProductEntity;
import com.bShop.repository.CategoryRepository;
import com.bShop.repository.ProductRepository;

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
