package com.rd.pizzadeliveryservice.repository;

import java.util.List;

import com.rd.pizzadeliveryservice.domain.Order;

public interface OrderRepository {
	Order getOrderById(Long id);
	Long save(Order order);
	List<Order> getAllOrders();
	List<Order> getOrdersByCustomerID(Long id);
}