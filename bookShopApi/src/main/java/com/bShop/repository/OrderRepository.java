package com.bShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bShop.model.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
