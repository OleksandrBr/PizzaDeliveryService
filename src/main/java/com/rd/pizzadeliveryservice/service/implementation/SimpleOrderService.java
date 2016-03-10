package com.rd.pizzadeliveryservice.service.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import com.rd.pizzadeliveryservice.domain.AccumulativeCard;
import com.rd.pizzadeliveryservice.domain.Order;
import com.rd.pizzadeliveryservice.domain.TotalOrderCostCalculator;
import com.rd.pizzadeliveryservice.repository.AccumulativeCardRepository;
import com.rd.pizzadeliveryservice.repository.OrderRepository;
import com.rd.pizzadeliveryservice.service.OrderService;

@Service
public class SimpleOrderService implements OrderService {
		//, ApplicationContextAware {
	//private ObjectFactory objectFactory = ObjectFactory.getInstance();

	//private ApplicationContext appContext;
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AccumulativeCardRepository accumulativeCardRepository;

	
	@Lookup(value="order")
	protected Order getNewOrder() {
		//Order order = (Order)appContext.getBean("order");
		return null;
	}

	/*public SimpleOrderService() throws InstantiationException, IllegalAccessException {
		orderRepository = (OrderRepository)objectFactory.createObject("orderRepository");
		pizzaRepository = (PizzaRepository)objectFactory.createObject("pizzaRepository");
	}*/
	
	public SimpleOrderService(){}

	public void destroy(){
		System.out.println("Destroy simple order service");
	}

	@Override
	public Order getOrderByID(Long id) {
		return orderRepository.getOrderById(id);
	}

	@Override
	@Transactional
	public Long save(Order order) {
		if(order.getCustomer() != null && order.getCustomer().getAccumulativeCard() != null){
			System.out.println("We here!");
			AccumulativeCard card = order.getCustomer().getAccumulativeCard();
			Long sum = card.getAccumulativeSum()+Math.round(TotalOrderCostCalculator.calculateOrderPrice(order));
			System.out.println(sum);
			card.setAccumulativeSum(sum);
			accumulativeCardRepository.save(card);
		}
		System.out.println("save order");
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getOrdersByCustomer(Long customerID) {
		return orderRepository.getOrdersByCustomerID(customerID);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.getAllOrders();
	}

	//@Override
	//public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	//	this.appContext = applicationContext;
	//}
}
