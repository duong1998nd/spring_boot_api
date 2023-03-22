package com.locShop.service;

import java.util.Optional;

import com.locShop.MyUserDetail.MyUserDetails;
import com.locShop.model.CartEntity;
import com.locShop.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.locShop.model.CartItemEntity;
import com.locShop.repository.CartItemRepository;

@Service
public class CartItemService implements CrudRepository<CartItemEntity, Long>{
	
	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private  CartService cartService;

	@Override
	public <S extends CartItemEntity> S save(S entity) {
		try {
			CartItemEntity findByCartIdAndProductId = findByCartIdAndProductId(entity.getCart(),entity.getProduct());
			if(findByCartIdAndProductId!=null){
				findByCartIdAndProductId.setQuantity(findByCartIdAndProductId.getQuantity()+entity.getQuantity());
				return (S) cartItemRepository.save(findByCartIdAndProductId);
			}else{
				return cartItemRepository.save(entity);
			}
		}catch (Exception e){
			return null;
		}
	}

	public CartItemEntity findByCartIdAndProductId(Long cartId, Long proId){
		CartItemEntity cartItem = cartItemRepository.findByCartIdAndProductId(cartId,proId);
		if(cartItem!=null){
			return cartItem;
		}
		return null;
	}

	@Override
	public <S extends CartItemEntity> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CartItemEntity> findById(Long id) {
		return cartItemRepository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<CartItemEntity> findAll() {
		return cartItemRepository.findAll();
	}

	public Iterable<CartItemEntity> findAllByCartId(MyUserDetails myUser) {
		 CartEntity cart = cartService.findByUserId(myUser.getId());
		return cartItemRepository.findAllByCartId(cart.getId());
	}

	@Override
	public Iterable<CartItemEntity> findAllById(Iterable<Long> ids) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CartItemEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends CartItemEntity> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	
	
}
