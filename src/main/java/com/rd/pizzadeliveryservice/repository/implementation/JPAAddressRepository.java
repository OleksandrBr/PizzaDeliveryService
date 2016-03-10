package com.rd.pizzadeliveryservice.repository.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.rd.pizzadeliveryservice.domain.Address;
import com.rd.pizzadeliveryservice.repository.AddressRepository;

@Repository("addressRepository")
public class JPAAddressRepository implements AddressRepository {
	
	@PersistenceContext(name = "HiberanteMySQL")
	private EntityManager em;

	@Override
	public Address getAddressById(Long id) {
		TypedQuery<Address> query = em.createQuery("select p from Address p where p.id = :id", Address.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	@Transactional
	public Long save(Address address) {
		if (address.getId() == null) {
			em.persist(address);
		}else {
			em.merge(address);
		}
		return address.getId();
	}

}
