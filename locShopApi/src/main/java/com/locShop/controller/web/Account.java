package com.locShop.controller.web;

import com.locShop.model.CartEntity;
import com.locShop.model.UserEntity;
import com.locShop.securityConfig.PasswordGenerator;
import com.locShop.service.AccountService;
import com.locShop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class Account {

	@Autowired
	private AccountService accountService;

	@Autowired
	private CartService cartService;

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