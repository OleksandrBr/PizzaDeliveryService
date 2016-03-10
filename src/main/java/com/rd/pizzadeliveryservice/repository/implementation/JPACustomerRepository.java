package com.rd.pizzadeliveryservice.repository.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.rd.pizzadeliveryservice.domain.Customer;
import com.rd.pizzadeliveryservice.repository.CustomerRepository;

@Repository("customerRepository")
public class JPACustomerRepository implements CustomerRepository{
	
	@PersistenceContext(name = "HiberanteMySQL")
	private EntityManager em;

	@Override
	public Customer getCustomerById(Long id) {
		TypedQuery<Customer> query = em.createQuery("select p from Customer p where p.id = :id", Customer.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	@Transactional
	public Long save(Customer customer) {
		if (customer == null)
			return null;

		if (customer.getId() == null) {
			em.persist(customer);
		} else {
			em.merge(customer);
		}
		return customer.getId();
	}

	@Override
	public Customer getCustomerByName(String name) {
		System.out.println("name: " + name);
		TypedQuery<Customer> query = em.createQuery("select p from Customer p where p.name = :name", Customer.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}

}
