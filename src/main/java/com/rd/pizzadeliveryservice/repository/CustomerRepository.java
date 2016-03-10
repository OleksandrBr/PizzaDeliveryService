package com.rd.pizzadeliveryservice.repository;

import com.rd.pizzadeliveryservice.domain.Customer;

public interface CustomerRepository {
	Customer getCustomerById(Long id);
	Customer getCustomerByName(String name);
	Long save(Customer customer);
}
