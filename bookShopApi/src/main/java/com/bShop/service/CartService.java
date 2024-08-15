package com.bShop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.bShop.model.CartEntity;
import com.bShop.repository.CartRepository;

@Service
public class CartService implements CrudRepository<CartEntity,Long>{
    @Autowired
    CartRepository cartRepository;

    public CartEntity findByUserId(Long userId){
        CartEntity cart = cartRepository.findByUserId(userId);
        return cart;
    }

    @Override
    public <S extends CartEntity> S save(S entity) {
        return cartRepository.save(entity);
    }

    @Override
    public <S extends CartEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CartEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<CartEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<CartEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(CartEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends CartEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
