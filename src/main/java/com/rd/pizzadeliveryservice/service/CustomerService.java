package com.rd.pizzadeliveryservice.service;

import com.rd.pizzadeliveryservice.domain.Customer;

public interface CustomerService {
	Customer getCustomerById(Long id);
	Customer getCustomerByName(String name);
}
