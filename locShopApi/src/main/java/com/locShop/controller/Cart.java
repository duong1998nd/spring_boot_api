package com.locShop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.locShop.model.CartEntity;
import com.locShop.model.CartItemEntity;
import com.locShop.service.CartItemService;

@RestController
@RequestMapping("api/cart")
public class Cart {
	
	@Autowired
	private CartItemService cartItemService;
	
	@GetMapping("/list")
	public Iterable<CartItemEntity> findAll(){
		return cartItemService.findAll();
	}
	
	@PostMapping("/items")
	public String addItem() {
		return null;
	}
	
	@PutMapping("/items/:id")
	public String addCart() {
		return null;
	}
	
	@DeleteMapping("/items/:id")
	public String deleteItem() {
		return null;
	}
	
}
