package com.rd.pizzadeliveryservice.repository;

import java.util.List;

import com.rd.pizzadeliveryservice.domain.Pizza;

public interface PizzaRepository {
	Pizza getPizzaByID(Long id);
	List<Pizza> getAllPizzas();
	Long save(Pizza pizza);
}