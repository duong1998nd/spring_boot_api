package com.bShop.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bShop.MyUserDetail.MyUserDetails;
import com.bShop.model.CartItemEntity;
import com.bShop.model.ProductEntity;
import com.bShop.securityConfig.JwtUtils;
import com.bShop.service.CartItemService;
import com.bShop.service.CartService;
import com.bShop.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

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

	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@GetMapping("/list")
	public Iterable<CartItemEntity> findAll(){
		MyUserDetails myUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return cartItemService.findAllByCartId(myUser);
	}

	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@PostMapping("/items")
	public CartItemEntity addItem(
			@RequestParam("productId") Long proId,
			@RequestParam("quantity") int quantity
			) {
			MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			ProductEntity pro = productService.findById(proId).orElse(null);
			CartItemEntity cartItem = new CartItemEntity(cartService.findByUserId(user.getId()), pro, quantity);
			return cartItemService.save(cartItem);
	}

	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@PutMapping("/items/{id}")
	public String updateCart(@PathVariable Long id,@RequestParam("quantity") int quantity) {
		boolean bl = cartItemService.updateQuantityProductInCart(id,quantity);
		if(bl){
			return "số lượng sản phẩm đã được cập nhật";
		}
		return "đã có lỗi xảy ra không thể cập nhật số lượng";
	}

	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@DeleteMapping("/items/{id}")
	public String deleteItem(@PathVariable("id") Long id) {
		try {
			cartItemService.deleteById(id);
			return "success";
		}catch (Exception e){
			e.printStackTrace();
			return "error";
		}
	}

}