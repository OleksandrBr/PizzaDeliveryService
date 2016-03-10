package com.rd.pizzadeliveryservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rd.pizzadeliveryservice.service.CustomerService;
import com.rd.pizzadeliveryservice.service.OrderService;
import com.rd.pizzadeliveryservice.service.PizzaService;

@Controller("homeController")
@RequestMapping(value="/")
public class HomeController {
	@Autowired
	protected PizzaService pizzaService;
	
	@Autowired
	protected CustomerService customerService;
	
	@Autowired
	protected OrderService orderService;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String viewAllPizzas(Model model){
		model.addAttribute("pizzas", pizzaService.getAllPizzas());		
		return "showallpizza";
	}
	
	@RequestMapping(value="/createTestOrder", method=RequestMethod.GET)
	public String createTestOrder(Model model){
		/*Customer customer = customerService.getCustomerById(2L);
		Order order = new Order();
		order.setCustomer(customer);
		
		Map<Pizza, Integer> pizzas = new HashMap<>();
		pizzas.put(pizzaService.getPizzaByID(1L), 2);
		
		order.setPizzaMap(pizzas);
		
		System.out.println(TotalOrderCostCalculator.calculateOrderPrice(order));
		orderService.save(order);
		
		model.addAttribute("pizzas", pizzaService.getAllPizzas());*/		
		return "redirect:pizza/show";
	}

}
