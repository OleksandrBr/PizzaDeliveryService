package com.rd.pizzadeliveryservice.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rd.pizzadeliveryservice.domain.Pizza;
import com.rd.pizzadeliveryservice.repository.PizzaRepository;
import com.rd.pizzadeliveryservice.service.PizzaService;

@Service(value="pizzaService")
public class PizzaServiceImplementation implements PizzaService{
	
	@Autowired
	private PizzaRepository pizzaRepository;

	@Override
	public Pizza getPizzaByID(Long id) {
		return pizzaRepository.getPizzaByID(id);
	}

	@Override
	public List<Pizza> getAllPizzas() {
		return pizzaRepository.getAllPizzas();
	}

	@Override
	public Long save(Pizza pizza) {
		return pizzaRepository.save(pizza);
	}

	@Override
	public Long update(Pizza pizza) {
		return pizzaRepository.save(pizza);
	}

}
