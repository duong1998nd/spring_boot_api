package com.locShop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.locShop.repository.AccountRepository;

import com.locShop.model.UserEntity;

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
}
