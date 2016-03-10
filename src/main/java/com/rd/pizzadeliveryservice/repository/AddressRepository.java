package com.rd.pizzadeliveryservice.repository;

import com.rd.pizzadeliveryservice.domain.Address;

public interface AddressRepository {
	Address getAddressById(Long id);
	Long save(Address address);
}
