package com.bShop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bShop.model.ProductEntity;
import com.bShop.model.UserEntity;
import com.bShop.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository AccountRepository;

	public List<UserEntity> getAllUsers(){
	    List<UserEntity>userRecords = new ArrayList<>();
	    AccountRepository.findAll().forEach(userRecords::add);
	    return userRecords;
	  }

	  public boolean addUser(UserEntity Account) {
		 if( AccountRepository.save(Account) != null) {
			 return true;
		 }else {
			 return false;
		 }
	  }

	  public UserEntity findByUsername(String username){
		UserEntity user = AccountRepository.findByUsername(username);
		return user;
	  }
	  
	  public UserEntity find(String username){
			UserEntity abc =  AccountRepository.getUserByUsername(username);
			return abc;
		}
}
