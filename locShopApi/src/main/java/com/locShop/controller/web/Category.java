package com.locShop.controller.web;

import com.locShop.model.CategoryEntity;
import com.locShop.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/web/category")
public class Category {

	@Autowired
	private CategoryService categoryService;


}