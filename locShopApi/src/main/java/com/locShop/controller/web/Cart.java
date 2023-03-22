package com.locShop.controller.web;

import java.util.Optional;

import com.locShop.MyUserDetail.MyUserDetails;
import com.locShop.dto.CartDto;
import com.locShop.model.CartEntity;
import com.locShop.model.ProductEntity;
import com.locShop.securityConfig.JwtUtils;
import com.locShop.service.CartService;
import com.locShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.locShop.model.CartItemEntity;
import com.locShop.service.CartItemService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/web/cart")
public class Cart {


	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private CartService cartService;

	@Autowired
	ProductService productService;

	@Autowired
	JwtUtils userJwt;

	@GetMapping("/list")
	public Iterable<CartItemEntity> findAll(){
		MyUserDetails myUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return cartItemService.findAllByCartId(myUser);
	}

	@PostMapping("/items")
	public CartItemEntity addItem(@RequestBody CartDto req) {
			MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ProductEntity pro = productService.findById(req.getProductId()).orElse(null);
			CartItemEntity cartItem = new CartItemEntity(cartService.findByUserId(user.getId()), pro, req.getQuantity());
			System.out.println(cartItem.getCart());
			System.out.println(cartItem.getProduct());
			System.out.println(cartItem.getQuantity());
			return cartItemService.save(cartItem);
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