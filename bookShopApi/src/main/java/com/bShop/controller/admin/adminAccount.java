package com.bShop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bShop.model.ProductEntity;
import com.bShop.model.UserEntity;
import com.bShop.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@CrossOrigin(origins = "http://192.168.1.4:8098", maxAge = 3600)
@RestController
@RequestMapping("api/admin/account")
public class adminAccount {

	@Autowired
	private AccountService accountService;

	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@GetMapping(value = "/show")
	public List<UserEntity> findAll(){
		return accountService.getAllUsers();
	}

	@Operation(security = {@SecurityRequirement(name = "BearerJWT")})
	@GetMapping(value = "/login-admin?name=?")
	public UserEntity findById(@RequestParam("username") String username ) {
		return accountService.find(username);
	}

}