package com.rd.pizzadeliveryservice.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rd.pizzadeliveryservice.domain.Customer;
import com.rd.pizzaservice.repository.templetes.ITRepositoryTestsTemplete;

public class JPACustomerRepositoryTest extends ITRepositoryTestsTemplete{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Ignore
	@Test
	public void CustomerRepository_testSaveMethod_NormalWork(){
		Customer customer = new Customer();
		customer.setAccumulativeCard(null);
		
		Long id = customerRepository.save(customer);
		System.out.println(id);
		assertNotNull(id);
		
	}
	
	@Ignore
	@Test
	public void CustomerRepository_testGetCustomerById_NormalWork(){
		Customer customer = customerRepository.getCustomerById(1L);
		
		Long id = customer.getId();
		System.out.println(id);
		assertNotNull(id);
		
	}
	
	@Ignore
	@Test
	public void CustomerRepository_testGetCustomerById_CustomerWithCard_NormalWork(){
		Customer customer = customerRepository.getCustomerById(2L);
		
		Long id = customer.getAccumulativeCard().getId();
		System.out.println(id);
		assertNotNull(id);
		
	}
}
