package com.bShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bShop.model.CategoryEntity;

import jakarta.transaction.Transactional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

	@Override
	@Query("SELECT c FROM CategoryEntity c")
	public List<CategoryEntity> findAll();

	@Query(value = "SELECT * FROM categories WHERE id=:id", nativeQuery = true)
	public CategoryEntity findById(@Param("id") int id);

	@Query(value = "INSERT INTO category (name, status) VALUES (:name, :status)", nativeQuery = true)
	@Modifying
	@Transactional
	public boolean insertCategory(@Param("name") String name, @Param("status") byte status);
}
