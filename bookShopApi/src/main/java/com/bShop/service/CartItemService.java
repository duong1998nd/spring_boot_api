package com.bShop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.bShop.MyUserDetail.MyUserDetails;
import com.bShop.model.CartEntity;
import com.bShop.model.CartItemEntity;
import com.bShop.repository.CartItemRepository;

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

	public boolean updateQuantityProductInCart(Long id,int quantity){
		CartItemEntity cartItem = cartItemRepository.findById(id).orElse(null);
		if(cartItem!=null){
			cartItem.setQuantity(quantity);
			return cartItemRepository.save(cartItem)!=null?true:false;
		}
		return false;
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
//		if(findById(id)!=null){
//			CartItemEntity cartItem = findById(id).orElse(null);
//			int update_quantity = cartItem.getQuantity()-1;
//			cartItem.setQuantity(update_quantity);
//			return cartItemRepository.save(cartItem)!=null?true:false;
//		}
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
		cartItemRepository.deleteById(id);
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
