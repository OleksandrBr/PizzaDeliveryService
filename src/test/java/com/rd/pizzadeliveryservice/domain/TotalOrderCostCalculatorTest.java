package com.rd.pizzadeliveryservice.domain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.runners.MockitoJUnitRunner;

import com.rd.pizzadeliveryservice.domain.AccumulativeCard;
import com.rd.pizzadeliveryservice.domain.Customer;
import com.rd.pizzadeliveryservice.domain.Order;
import com.rd.pizzadeliveryservice.domain.Pizza;
import com.rd.pizzadeliveryservice.domain.PizzaType;
import com.rd.pizzadeliveryservice.domain.TotalOrderCostCalculator;

@RunWith(MockitoJUnitRunner.class)
public class TotalOrderCostCalculatorTest {
	
	public static double DELTA = 0.001;
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPrice_ThrowException_WithOutPizza(){
		Map<Pizza, Integer> pizzas = new HashMap<>();
		
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		totalOrderCostCalculator.calculateTotalOrderPrise(pizzas);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPrice_ThrowException_BiggestThenTenPizzas(){
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1L, "Some name", 10.11, PizzaType.MEAT), 15);
		
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		totalOrderCostCalculator.calculateTotalOrderPrise(pizzas);
		
	}
	
	@Test
	public void testCalculateTotalOrderPrice_OnePizza(){
		Map<Pizza, Integer> pizzas = new HashMap<>();
		double pizzaPrice = 45.60;
		pizzas.put(new Pizza(1L, "Some name", pizzaPrice, PizzaType.MEAT), 1);
		double expectedPrice = 45.60;
		
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		double price = totalOrderCostCalculator.calculateTotalOrderPrise(pizzas);
		
		assertEquals(expectedPrice, price, DELTA);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPrice_ThrowException_PizzaCountIsNegative(){
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1L, "Some name", 10.11, PizzaType.MEAT), 3);
		pizzas.put(new Pizza(1L, "Some name", 10.11, PizzaType.MEAT), -2);
		
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		totalOrderCostCalculator.calculateTotalOrderPrise(pizzas);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCalculateTotalOrderPrice_ThrowException_PizzaCountIsZero(){
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1L, "Some name", 10.11, PizzaType.MEAT), 3);
		pizzas.put(new Pizza(1L, "Some name", 10.11, PizzaType.MEAT), 0);
		
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		totalOrderCostCalculator.calculateTotalOrderPrise(pizzas);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testCalculateTotalOrderPrice_ThrowException_IfPizzaIsNull(){
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1L, "Some name", 10.11, PizzaType.MEAT), 3);
		pizzas.put(null, 3);
		
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		totalOrderCostCalculator.calculateTotalOrderPrise(pizzas);
		
	}
	
	@Test(expected = NullPointerException.class)
	public void testCalculateTotalOrderPrice_ThrowException_IfPizzasIsNull(){
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		totalOrderCostCalculator.calculateTotalOrderPrise(null);
		
	}
	
	@Test
	public void testCalculateTotalOrderPrice_ThreeDifferentPizza(){
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1L, "Some name", 45.60, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(2L, "Some name", 55.60, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(3L, "Some name", 35.60, PizzaType.MEAT), 1);
		double expectedPrice = 136.80;
		
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		double price = totalOrderCostCalculator.calculateTotalOrderPrise(pizzas);
		
		assertEquals(expectedPrice, price, DELTA);
	}
	
	@Test
	public void testCalculateTotalOrderPrice_FourDifferentPizza(){
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1L, "Some name", 45.60, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(2L, "Some name", 55.60, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(3L, "Some name", 35.60, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(4L, "Some name", 45.60, PizzaType.MEAT), 1);
		double expectedPrice = 182.40;
		
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		double price = totalOrderCostCalculator.calculateTotalOrderPrise(pizzas);
		
		assertEquals(expectedPrice, price, DELTA);
	}
	
	@Test
	public void testCalculateTotalOrderPrice_FiveDifferentPizza(){
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1L, "Some name", 30.00, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(2L, "Some name", 60.00, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(3L, "Some name", 30.00, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(4L, "Some name", 45.00, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(5L, "Some name", 45.00, PizzaType.MEAT), 1);
		//60 - max price
		//60 - 30% = 42 
		// 30+30+45+45+42 = 192
		double expectedPrice = 192.00;
		
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		double price = totalOrderCostCalculator.calculateTotalOrderPrise(pizzas);
		
		assertEquals(expectedPrice, price, DELTA);
	}
	
	@Test
	public void testCalculateTotalOrderPrice_FiveDifferentPizzaTwoMaxPrice(){
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(new Pizza(1L, "Some name", 30.00, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(2L, "Some name", 60.00, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(3L, "Some name", 60.00, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(4L, "Some name", 45.00, PizzaType.MEAT), 1);
		pizzas.put(new Pizza(5L, "Some name", 45.00, PizzaType.MEAT), 1);
		//60 - max price
		//60 - 30% = 42 
		// 30+60+45+45+42 = 222
		double expectedPrice = 222.00;
		
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		double price = totalOrderCostCalculator.calculateTotalOrderPrise(pizzas);
		
		assertEquals(expectedPrice, price, DELTA);
	}
	
	@Test
	public void testCountDiscount_normalPercent(){
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		double discount = totalOrderCostCalculator.countDiscount(100, 30);
		double expectedDiscount = 30.00;
		
		assertEquals(expectedDiscount, discount, DELTA);
	}
	
	@Test
	public void testCountDiscount_zeroPercent(){
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		double discount = totalOrderCostCalculator.countDiscount(100, 0);
		double expectedDiscount = 0.00;
		
		assertEquals(expectedDiscount, discount, DELTA);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCountDiscount_negativePercent(){
		TotalOrderCostCalculator totalOrderCostCalculator = new TotalOrderCostCalculator();
		totalOrderCostCalculator.countDiscount(100, -30);
	}

	@Spy
	private AccumulativeCard card;	
	@Mock private Customer customer;	
	@Mock private Pizza pizza;
	private Order order;
	
	@Before
    public void setUp()
        throws Exception
    {
        MockitoAnnotations.initMocks( this );
        order = new Order();
        order.setCustomer(customer);
        order.addPizzaToOrder(pizza);
    }
	
	@Test
	public void testCalculateOrderPrice_withAccumulativeCard_sumWitoutDiscount(){
		when(card.getAccumulativeSum()).thenReturn(50L);
		when(pizza.getPrice()).thenReturn(45.5);
		when(customer.getAccumulativeCard()).thenReturn(card);
		double expectedPrice = 45.5;
		
		double price = TotalOrderCostCalculator.calculateOrderPrice(order);
		
		verify(card, VerificationModeFactory.times(2)).getAccumulativeSum();
		assertEquals(expectedPrice, price, DELTA);
	}
	
	@Test
	public void testCalculateOrderPrice_withAccumulativeCard_sunWithOnePercentDiscount(){
		when(card.getAccumulativeSum()).thenReturn(500L);
		when(pizza.getPrice()).thenReturn(50.0);
		when(customer.getAccumulativeCard()).thenReturn(card);
		double expectedPrice = 49.5;
		
		double price = TotalOrderCostCalculator.calculateOrderPrice(order);
		
		verify(card, VerificationModeFactory.times(2)).getAccumulativeSum();
		assertEquals(expectedPrice, price, DELTA);
	}
	
	@Test
	public void testCalculateOrderPrice_withAccumulativeCard_sunWithThreePercentDiscount(){
		when(card.getAccumulativeSum()).thenReturn(3000L);
		when(pizza.getPrice()).thenReturn(50.0);
		when(customer.getAccumulativeCard()).thenReturn(card);
		double expectedPrice = 48.5;
		
		double price = TotalOrderCostCalculator.calculateOrderPrice(order);
		
		verify(card, VerificationModeFactory.times(2)).getAccumulativeSum();
		assertEquals(expectedPrice, price, DELTA);
	}
	
	@Test
	public void testCalculateOrderPrice_withAccumulativeCard_sunWithFivePercentDiscount(){
		when(card.getAccumulativeSum()).thenReturn(5000L);
		when(pizza.getPrice()).thenReturn(50.0);
		when(customer.getAccumulativeCard()).thenReturn(card);
		double expectedPrice = 47.5;
		
		double price = TotalOrderCostCalculator.calculateOrderPrice(order);
		
		verify(card, VerificationModeFactory.times(2)).getAccumulativeSum();
		assertEquals(expectedPrice, price, DELTA);
	}
	
	@Test
	public void testCalculateOrderPrice_withAccumulativeCard_sumEqualNull(){
		when(card.getAccumulativeSum()).thenReturn(null);
		when(pizza.getPrice()).thenReturn(45.5);
		when(customer.getAccumulativeCard()).thenReturn(card);
		double expectedPrice = 45.5;
		
		double price = TotalOrderCostCalculator.calculateOrderPrice(order);
		
		verify(card, VerificationModeFactory.times(1)).getAccumulativeSum();
		assertEquals(expectedPrice, price, DELTA);
	}
	
	@Test
	public void testCalculateOrderPrice_withoutAccumulativeCard(){
		when(pizza.getPrice()).thenReturn(45.5);
		when(customer.getAccumulativeCard()).thenReturn(null);
		double expectedPrice = 45.5;
		
		double price = TotalOrderCostCalculator.calculateOrderPrice(order);
		
		verify(card, VerificationModeFactory.times(0)).getAccumulativeSum();
		assertEquals(expectedPrice, price, DELTA);
	}
}
