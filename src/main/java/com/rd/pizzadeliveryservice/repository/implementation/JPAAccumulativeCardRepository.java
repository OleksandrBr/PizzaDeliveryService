package com.rd.pizzadeliveryservice.repository.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.rd.pizzadeliveryservice.domain.AccumulativeCard;
import com.rd.pizzadeliveryservice.repository.AccumulativeCardRepository;

@Repository("accumulativeCardRepository")
public class JPAAccumulativeCardRepository implements AccumulativeCardRepository {
	
	@PersistenceContext(name = "HiberanteMySQL")
	private EntityManager em;

	@Override
	public AccumulativeCard getAccumulativeCardById(Long id) {
		TypedQuery<AccumulativeCard> query = em.createQuery("select p from AccumulativeCard p where p.id = :id", AccumulativeCard.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	@Transactional
	public Long save(AccumulativeCard accumulativeCard) {
		if (accumulativeCard == null)
			return null;

		if (accumulativeCard.getId() == null) {
			em.persist(accumulativeCard);
		} else {
			em.merge(accumulativeCard);
		}
		return accumulativeCard.getId();
	}

}
