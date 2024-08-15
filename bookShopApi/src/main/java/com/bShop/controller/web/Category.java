package com.bShop.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bShop.model.CategoryEntity;
import com.bShop.service.CategoryService;

@RestController
@RequestMapping("api")
public class Category {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/category")
	public Iterable<CategoryEntity> listCategory(){
		return categoryService.findAll();
	}

}