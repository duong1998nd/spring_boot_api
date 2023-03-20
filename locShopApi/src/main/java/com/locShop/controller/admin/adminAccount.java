package com.locShop.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.locShop.model.UserEntity;
import com.locShop.securityConfig.PasswordGenerator;
import com.locShop.service.AccountService;

@RestController
@RequestMapping("api/admin/account")
public class adminAccount {

	@Autowired
	private AccountService accountService;

	@GetMapping(value = "/show")
	public List<UserEntity> findAll(){
		return accountService.getAllUsers();
	}

	@RequestMapping(value = "/login-admin", method = RequestMethod.POST)
	public String loginAdmin() {
		return "login";
	}
}