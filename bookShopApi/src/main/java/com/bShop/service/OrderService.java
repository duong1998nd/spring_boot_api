package com.bShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bShop.model.OrderEntity;
import com.bShop.model.OrderItemsEntity;
import com.bShop.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<OrderEntity> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public OrderEntity saveOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
    
//    public OrderEntity addItemToOrder(Long orderId, OrderItemsEntity orderItem) {
//        Optional<OrderEntity> orderOptional = getOrderById(orderId);
//        if (orderOptional.isPresent()) {
//        	OrderEntity order = orderOptional.get();
//            order.addItem(orderItem);
//            return saveOrder(order);
//        } else {
//            throw new RuntimeException("Order not found");
//        }
//    }
    public OrderEntity addItemToOrder(Long orderId, OrderItemsEntity orderItem) {
        Optional<OrderEntity> orderOptional = getOrderById(orderId);
        if (orderOptional.isPresent()) {
        	OrderEntity order = orderOptional.get();
            order.addItem(orderItem);
            order.setTotalAmount(order.getTotalAmount() + orderItem.getTotalPrice());
            return saveOrder(order);
        } else {
            throw new RuntimeException("Order not found");
        }
    }

}


