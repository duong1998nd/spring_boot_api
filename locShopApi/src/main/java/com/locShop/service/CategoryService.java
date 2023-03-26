package com.locShop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.locShop.model.CategoryEntity;
import com.locShop.repository.CategoryRepository;

@Service
public class CategoryService implements CrudRepository<CategoryEntity, Long> {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public <S extends CategoryEntity> S save(S entity) {
		if(entity.getId()!=null){
			CategoryEntity cat = categoryRepository.findById(entity.getId()).orElse(null);
			if(cat!=null){
				cat.setName(entity.getName());
				cat.setStatus(entity.getStatus());
				return (S) categoryRepository.save(cat);
			}
		}
		return categoryRepository.save(entity);
	}

	@Override
	public <S extends CategoryEntity> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<CategoryEntity> findAll() {
		List<CategoryEntity> cats = categoryRepository.findAll();
		return cats;
	}

	@Override
	public Iterable<CategoryEntity> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public void delete(CategoryEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends CategoryEntity> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<CategoryEntity> findById(Long id) {
		return categoryRepository.findById(id);
	}

}
