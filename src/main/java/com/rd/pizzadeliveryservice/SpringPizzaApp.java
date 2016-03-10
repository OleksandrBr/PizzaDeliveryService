package com.rd.pizzadeliveryservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rd.pizzadeliveryservice.domain.Pizza;
import com.rd.pizzadeliveryservice.repository.PizzaRepository;
import com.rd.pizzadeliveryservice.service.PizzaService;

public class SpringPizzaApp {
	public static void main(String[] args) throws Exception {
		System.out.println("pizzadeliveryservice");
        ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext("classpath:/repositoryConfig.xml");
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"classpath:/appContext.xml"}, repositoryContext);
        PizzaRepository pizzaRepository = (PizzaRepository)appContext.getBean("pizzaRepository");
        System.out.println(pizzaRepository);

        String[] beans = appContext.getBeanDefinitionNames();
        System.out.println("context start");
        for (String string : beans) {
            System.out.println(string);
        }
        System.out.println("context finish");
        
        for (Pizza piz : pizzaRepository.getAllPizzas()){
        	System.out.println(piz);
        }
        
        org.springframework.beans.factory.config.PropertyPlaceholderConfigurer config = (org.springframework.beans.factory.config.PropertyPlaceholderConfigurer)appContext.getBean("propertyConfigure");
        System.out.println(config.toString());
        
        //pizzaRepository.save(new Pizza(null, "Vegetarian", 71.30, PizzaType.VEGETERIAN));
        System.out.println(pizzaRepository.getPizzaByID(1L));
        
        PizzaService pizzaService = (PizzaService)appContext.getBean("pizzaService");
        System.out.println(pizzaService);

        appContext.close();
        repositoryContext.close();
    }

}
