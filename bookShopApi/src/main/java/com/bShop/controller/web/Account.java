package com.bShop.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bShop.model.CartEntity;
import com.bShop.model.ProductEntity;
import com.bShop.model.UserEntity;
import com.bShop.securityConfig.PasswordGenerator;
import com.bShop.service.AccountService;
import com.bShop.service.CartService;

@RestController
@RequestMapping("api")
public class Account {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CartService cartService;

	@GetMapping("/account/username={username}")
	public UserEntity findByName(@PathVariable("username") String username){
		UserEntity acc = accountService.find(username);
		return acc;
	}
	
	@PostMapping("/account/register")
	public String addAccount(
			@RequestParam("fullname") String fullname,
			@RequestParam("email") String email,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("phone") String phone
	) {
		System.out.println(fullname);
		System.out.println(email);
		System.out.println(username);
		System.out.println(password);
		System.out.println(phone);
		UserEntity acc = new UserEntity(fullname,email,username,password,phone);
		acc.setPassword(new PasswordGenerator().bcryptPassword(password));
		try {
			accountService.addUser(acc);
			UserEntity user = accountService.findByUsername(username);
			CartEntity cart = new CartEntity();
			cart.setUser(user);
			cartService.save(cart);
			return "thêm mới thành công";
		}catch (Exception e){
			e.printStackTrace();
			return "thêm mới thất bại";
		}
	}
}