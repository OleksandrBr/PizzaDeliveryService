package com.rd.pizzadeliveryservice.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rd.pizzadeliveryservice.domain.Order;
import com.rd.pizzadeliveryservice.domain.Pizza;
import com.rd.pizzadeliveryservice.domain.dto.OrderDTO;

@RestController
public class PizzaRESTController extends AbstractContoller{
	
	@RequestMapping(method = RequestMethod.GET, value = "hello")
	public ResponseEntity<String> hello(){
		return new ResponseEntity<>("hello from RESTController", HttpStatus.I_AM_A_TEAPOT);
	}
	
	@RequestMapping(value="/pizzas/{pizzaId}", method = RequestMethod.GET)
	public ResponseEntity<Pizza> getPizzaById(@PathVariable("pizzaId") Pizza pizza){
		if (pizza == null) return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Pizza>(pizza, HttpStatus.OK);
	}
	
	@RequestMapping(value="/pizzas/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<Pizza>> getAllPizzas(){
		return new ResponseEntity<List<Pizza>>(pizzaService.getAllPizzas(), HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.POST,
			value="/pizzas/add", 
			headers = "content-type=application/json"
			)
	public ResponseEntity<Pizza> createNewPizza(@RequestBody Pizza pizza, UriComponentsBuilder builder){
		System.out.println(pizza);
		pizzaService.save(pizza);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				builder.path("/pizzas/{id}")
				.buildAndExpand(pizza.getId()).toUri());
		
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/orders/{orderId}", method = RequestMethod.GET)
	public ResponseEntity<Order> getOrderById(@PathVariable("orderId") Order order){
		if (order == null) return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.POST,
			value="/orders/add", 
			headers = "content-type=application/json"
			)
	public ResponseEntity<Order> createNewOrder(@RequestBody OrderDTO orderDTO, UriComponentsBuilder builder){
		System.out.println(orderDTO);
		orderDTO.setCustomerService(customerService);
		orderDTO.setPizzaService(pizzaService);
		Order order = orderDTO.createNewOrder();
		orderService.save(order);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				builder.path("/orders/{id}")
				.buildAndExpand(order.getId()).toUri());
		
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			method = RequestMethod.POST,
			value="/orders/addfull", 
			headers = "content-type=application/json"
			)
	public ResponseEntity<Order> createNewFullOrder(@RequestBody Order order, UriComponentsBuilder builder){
		System.out.println(order);
		orderService.save(order);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				builder.path("/orders/{id}")
				.buildAndExpand(order.getId()).toUri());
		
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "test")
	public ResponseEntity<OrderDTO> getTest2(){
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setName("FFF");
		orderDTO.setCustomer(5L);
		Map<String,Integer> map = new HashMap<>();
		map.put("1", 2);
		orderDTO.setPizzaMap(map);
		return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
	}

}
