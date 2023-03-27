package com.locShop.controller.web;

import com.locShop.MyUserDetail.MyUserDetails;
import com.locShop.model.ProductEntity;
import com.locShop.securityConfig.JwtUtils;
import com.locShop.service.CartService;
import com.locShop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.locShop.model.CartItemEntity;
import com.locShop.service.CartItemService;

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