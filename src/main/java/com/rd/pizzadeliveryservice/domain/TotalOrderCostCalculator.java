package com.rd.pizzadeliveryservice.domain;

import java.util.Map;
import java.util.Map.Entry;

public class TotalOrderCostCalculator {
	
	public static double calculateTotalOrderPrise(Map<Pizza, Integer> pizzas){
		if (pizzas == null) throw new NullPointerException("");
		
		double price = 0;
		int amount = 0;
		Pizza biggestPricePizza = null;
		for (Entry<Pizza, Integer> pizzaEntry : pizzas.entrySet()){
			int amoutPizza = pizzaEntry.getValue();
			if (amoutPizza < 1) throw new IllegalArgumentException("");
			
			Pizza pizza = pizzaEntry.getKey();
			if (pizza == null) throw new NullPointerException("");
			
			if (biggestPricePizza == null) biggestPricePizza = pizza;
			else {if (biggestPricePizza.getPrice() < pizza.getPrice()) biggestPricePizza = pizza;}
			
			price += amoutPizza*pizza.getPrice();
			amount += amoutPizza;
		}	
		
		if (amount > 10 || amount < 1) throw new IllegalArgumentException("");
		
		if (amount > 4) price-= countDiscount(biggestPricePizza.getPrice(), 30);
		
		return price;
	}
	
	public static double countDiscount(double price, double percent){
		if (percent < 0) throw new IllegalArgumentException();
		return price*percent/100;
	}
	
	
	/**
	 * 
	 * @param order
	 * @return
	 * if sum < 100 then discount 0%
	 * if sum >= 100 and sum < 1000 discount 1%
	 * if sum >= 1000 and sum < 5000 discount 3%
	 * if sum >= 5000 discount 5%
	 */
	private static double accumulativeDiscount(Order order, double price){
		if (order == null) {return 0.0;}
		if (order.getCustomer() == null) { return 0.0;}
		if (order.getCustomer().getAccumulativeCard() == null) { return 0.0;}
		if (order.getCustomer().getAccumulativeCard().getAccumulativeSum() == null) { return 0.0;}
		
		Long sum = order.getCustomer().getAccumulativeCard().getAccumulativeSum();
		
		if (sum >= 100 && sum < 1000) {return price/100;}
		if (sum >= 1000 && sum < 5000) {return 3.0*price/100;}
		if (sum >= 5000) {return 5.0*price/100;}
		
		return 0.0;
		
	}
	
	public static double calculateOrderPrice(Order order){
		if (order.getPizzaMap().isEmpty()) return 0.0;
		double price = calculateTotalOrderPrise(order.getPizzaMap());
		
		double discountAccumulativeCard = accumulativeDiscount(order, price);
		
		if (price <= discountAccumulativeCard) {return 0.0;}
		return price - discountAccumulativeCard;
	}

}
