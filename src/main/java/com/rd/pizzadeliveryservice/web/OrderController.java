package com.rd.pizzadeliveryservice.web;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rd.pizzadeliveryservice.domain.Customer;
import com.rd.pizzadeliveryservice.domain.Order;
import com.rd.pizzadeliveryservice.service.OrderService;

@Controller("orderController")
@RequestMapping(value="/orders")
@Secured("ROLE_USER")
public class OrderController extends AbstractContoller{
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public String viewOrders(Model model){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Customer customer = customerService.getCustomerByName(authentication.getName().toString());
		
		model.addAttribute("accumulCard", customer.getAccumulativeCard());
		
		Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
		boolean isAdmin = false;
		for(GrantedAuthority ga : roles){ if ("ROLE_ADMIN".equals(ga.toString())) isAdmin = true;}
		
		if (isAdmin) {
			model.addAttribute("orders", orderService.getAllOrders());
		}else model.addAttribute("orders", orderService.getOrdersByCustomer(customer.getId()));
		
		return "showOrders";
	}
	
	@RequestMapping(value="/showOrder", method=RequestMethod.GET)
	public String viewOrder(@RequestParam("id") Order order, Model model){
		
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//model.addAttribute("name", authentication.getName().toString());		
		//model.addAttribute("roles", authentication.getAuthorities().toString());
		
		model.addAttribute("order", order);
		model.addAttribute("pizzas", order.getPizzaMap().entrySet());
		
		return "showOrder";
	}

}
