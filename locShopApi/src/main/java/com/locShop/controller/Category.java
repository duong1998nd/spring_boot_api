package com.locShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locShop.model.CategoryEntity;
import com.locShop.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class Category {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add")
	public String insertCategory(@Valid @RequestBody CategoryEntity cat) {
		if(categoryService.save(cat) == null) {
			return "thêm mới thất bại";
		}else {
			return "thêm mới thành công";
		}
	}
}
