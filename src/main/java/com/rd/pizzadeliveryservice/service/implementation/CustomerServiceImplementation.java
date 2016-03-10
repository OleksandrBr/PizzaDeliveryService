package com.rd.pizzadeliveryservice.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rd.pizzadeliveryservice.domain.Customer;
import com.rd.pizzadeliveryservice.repository.CustomerRepository;
import com.rd.pizzadeliveryservice.service.CustomerService;

@Service(value="customerService")
public class CustomerServiceImplementation implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.getCustomerById(id);
	}

	@Override
	public Customer getCustomerByName(String name) {
		return customerRepository.getCustomerByName(name);
	}

}
