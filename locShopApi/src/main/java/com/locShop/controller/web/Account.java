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
	public String addAccount(@RequestBody UserEntity acc) {
		acc.setPassword(new PasswordGenerator().bcryptPassword(acc.getPassword()));
		try {
			accountService.addUser(acc);
			UserEntity user = accountService.findByUsername(acc.getUsername());
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