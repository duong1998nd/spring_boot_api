package com.locShop.controller.web;

import com.locShop.model.CategoryEntity;
import com.locShop.model.ProductEntity;
import com.locShop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class Category {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/category")
	public Iterable<CategoryEntity> listCategory(){
		return categoryService.findAll();
	}

	@GetMapping("/category?productId={id}")
	public List<ProductEntity> listProductOrCategory(@PathVariable int id){

		return null;
	}
}