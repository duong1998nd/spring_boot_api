package com.bShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.bShop.MyUserDetail.MyUserDetails;
import com.bShop.model.UserEntity;
import com.bShop.repository.AccountRepository;

@Repository
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = accountRepository.getUserByUsername(username);

		if(user == null) {
			throw new UsernameNotFoundException("khong tim thay user");
		}
		return new MyUserDetails(user);
	}

}
