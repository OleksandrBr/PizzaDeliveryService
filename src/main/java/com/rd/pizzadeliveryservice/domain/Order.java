package com.rd.pizzadeliveryservice.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "order")
@Scope(value="prototype")
@Entity @Table(name="orders")
public class Order implements Serializable {
	//static long count;
	//private List<Pizza> list;
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	@ManyToOne
	@JoinColumn(name="id_customer", nullable=true)
	private Customer customer;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="order_pizza",
			joinColumns=@JoinColumn(name="id_order"))
	@MapKeyJoinColumn(name="id_pizza")
	@Column(name="amount")
	private Map<Pizza, Integer> pizzaMap;
	
	public Order() {
		pizzaMap = new HashMap<>();
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ",  customer=" //+ customer
				+ "]";
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Map<Pizza, Integer> getPizzaMap() {
		return pizzaMap;
	}

	public void setPizzaMap(Map<Pizza, Integer> pizzaMap) {
		this.pizzaMap = pizzaMap;
	}

	public void destroy(){
		System.out.println("destroy");
	}
	
	public Order(Customer customer, List<Pizza> list) {
		//this.list = list;
		this.customer = customer;
	}
	public Order(Long id, List<Pizza> list, Customer customer) {
		this.id = id;
		//this.list = list;
		this.customer = customer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addPizzaToOrder(Pizza pizza){
		if (pizzaMap.containsKey(pizza)){
			pizzaMap.put(pizza, pizzaMap.get(pizza)+1);
		}else pizzaMap.put(pizza, 1);
	}
}
