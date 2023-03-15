package com.locShop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.locShop.model.UserEntity;

public interface AccountRepository extends CrudRepository<UserEntity, Long>{
	
	@Query("SELECT u FROM UserEntity u WHERE u.username = :username AND role='ADMIN'")
	public UserEntity getUserByUsername(@Param("username") String username);
}
