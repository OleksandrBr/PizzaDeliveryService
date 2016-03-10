package com.rd.pizzadeliveryservice.web;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.rd.pizzadeliveryservice.domain.Order;
import com.rd.pizzadeliveryservice.domain.Pizza;
import com.rd.pizzadeliveryservice.exception.OrderNotFoundException;
import com.rd.pizzadeliveryservice.exception.PizzaNotFoundException;
import com.rd.pizzadeliveryservice.service.CustomerService;
import com.rd.pizzadeliveryservice.service.OrderService;
import com.rd.pizzadeliveryservice.service.PizzaService;

public abstract class AbstractContoller {

    @Autowired
    protected PizzaService pizzaService;

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected CustomerService customerService;

    public Pizza getPizzaById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID<0");
        }
        Pizza pizza = pizzaService.getPizzaByID(id);
        if (pizza == null) {
            throw new PizzaNotFoundException("Pizza id" + id + " not found");
        }
        return pizza;
    }

    @InitBinder
    public void pizzaBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Pizza.class,
                new PropertyEditorSupport() {
            @Override
            public void setAsText(String pizzaId) {
                Pizza pizza = null;
                if (pizzaId != null && !pizzaId.trim().isEmpty()) {
                    Long id = Long.valueOf(pizzaId);
                    pizza = getPizzaById(id);
                }
                setValue(pizza);
            }
        }
        );
    }

    public Order getOrderById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID<=0");
        }
        Order order = orderService.getOrderByID(id);
        if (order == null) {
            throw new OrderNotFoundException("Order id = " + id + " not found");
        }
        return order;
    }

    @InitBinder
    public void orderBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Order.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String orderId) {
                System.out.println("Ooops we here");
                Order order = null;
                if (orderId != null && !orderId.trim().isEmpty()) {
                    Long id = Long.parseLong(orderId);
                    order = getOrderById(id);
                }
                setValue(order);
            }
        });
    }

}
