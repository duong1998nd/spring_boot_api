package com.bShop.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bShop.model.OrderEntity;
import com.bShop.model.OrderItemsEntity;
import com.bShop.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class Order {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrderEntity createOrder(@RequestBody OrderEntity order) {
        return orderService.saveOrder(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderEntity> addItemToOrder(@PathVariable Long orderId, @RequestBody OrderItemsEntity orderItem) {
        OrderEntity updatedOrder = orderService.addItemToOrder(orderId, orderItem);
        return ResponseEntity.ok(updatedOrder);
    }
}


