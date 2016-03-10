package com.rd.pizzadeliveryservice.repository;

import com.rd.pizzadeliveryservice.domain.AccumulativeCard;

public interface AccumulativeCardRepository {
	AccumulativeCard getAccumulativeCardById(Long id);
	Long save(AccumulativeCard accumulativeCard);
}
