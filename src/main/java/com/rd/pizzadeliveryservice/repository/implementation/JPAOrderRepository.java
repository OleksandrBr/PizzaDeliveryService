package com.rd.pizzadeliveryservice.repository.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.rd.pizzadeliveryservice.domain.Order;
import com.rd.pizzadeliveryservice.repository.OrderRepository;

@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository {
	
	@PersistenceContext(name = "HiberanteMySQL")
	private EntityManager em;

	@Override
	public Order getOrderById(Long id) {
		TypedQuery<Order> query = em.createQuery("select p from Order p where p.id = :id", Order.class);
		query.setParameter("id", id);
		//Order order = new Order();
		return query.getSingleResult();
	}

	@Override
	@Transactional
	public Long save(Order order) {
		if (order == null)
			return null;
		
		//OrderDTO orderDTO = new OrderDTO(order);

		if (order.getId() == null) {
			em.persist(order);
		} else {
			em.merge(order);
		}
		//order.setId(orderDTO.getId());
		return order.getId();
		//return 1L;
	}

	@Override
	public List<Order> getAllOrders() {
		TypedQuery<Order> query = em.createQuery("select p from Order p", Order.class);
		return query.getResultList();
	}

	@Override
	public List<Order> getOrdersByCustomerID(Long id) {
		TypedQuery<Order> query = em.createQuery("select p from Order p where p.customer.id = :id", Order.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

}
