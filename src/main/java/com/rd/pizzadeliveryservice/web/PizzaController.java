package com.rd.pizzadeliveryservice.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.rd.pizzadeliveryservice.domain.Customer;
import com.rd.pizzadeliveryservice.domain.Order;
import com.rd.pizzadeliveryservice.domain.Pizza;
import com.rd.pizzadeliveryservice.domain.TotalOrderCostCalculator;
import com.rd.pizzadeliveryservice.exception.PizzaNotFoundException;

@Controller("pizzaController")
@RequestMapping(value="/pizza")
@Secured("ROLE_USER")
@SessionAttributes("order")
public class PizzaController extends AbstractContoller{
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello Spring MVC";		
	}
	
	@ModelAttribute("order")
	public Order generateNewOrder() {
		Order order = new Order();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		Customer customer = customerService.getCustomerByName(authentication.getName().toString());
		
		order.setCustomer(customer);
		
		return order;
	}
	
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public String viewPizzas(Model model){
		model.addAttribute("pizzas", pizzaService.getAllPizzas());
		
		Order order = null;
		if (!model.containsAttribute("order")) model.addAttribute("order", generateNewOrder());
		order = (Order) model.asMap().get("order");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		model.addAttribute("name", authentication.getName().toString());		
		model.addAttribute("roles", authentication.getAuthorities().toString());
		model.addAttribute("pizzaSet", order.getPizzaMap().entrySet());
		model.addAttribute("totalPrice", TotalOrderCostCalculator.calculateOrderPrice(order));
		
		return "show";
	}
	
	@RequestMapping(value="addtoorder", method=RequestMethod.POST)
	public String addPizzaToOrder(@RequestParam("id") Pizza pizza, @ModelAttribute("order") Order order, Model model){
		order.addPizzaToOrder(pizza);
		return "redirect:show";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String createPizza(Model model){
		model.addAttribute("pizza", new Pizza());
		return "addpizza";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String createNewPizza(@ModelAttribute Pizza pizza){
		pizzaService.save(pizza);
		return "redirect:show";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public String editPizza(@RequestParam("id") Pizza pizza, Model model){
		model.addAttribute("pizza", pizza);
		return "editpizza";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public String savePizza(@ModelAttribute Pizza pizza){
		pizzaService.update(pizza);
		return "redirect:show";
	}
	
	@RequestMapping(value="/saveOrder", method=RequestMethod.POST)
	public String saveOrder(@ModelAttribute Order order, Model model){
		System.out.println("save order" + order + " size" + order.getPizzaMap().size());
		if (order.getId()!=null) { order.setId(null);}
		orderService.save(order);
		order = generateNewOrder();
		System.out.println(order);
		model.addAttribute("order", order);
		return "redirect:show";
	}
	
	@ExceptionHandler(PizzaNotFoundException.class)
	public ModelAndView exceptionHandler(Exception exception, HttpServletRequest request){
		ModelAndView model = new ModelAndView("error");
		model.addObject("ex", exception);
		model.addObject("url", request.getRequestURI());
		return model;
	}

}
