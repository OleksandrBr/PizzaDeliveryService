package com.rd.pizzadeliveryservice.service;

import java.util.List;

import com.rd.pizzadeliveryservice.domain.Order;

public interface OrderService {

	Order getOrderByID(Long id);
	Long save(Order order);
	List<Order> getOrdersByCustomer(Long customerID);
	List<Order> getAllOrders();

}