package com.rd.pizzadeliveryservice.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.rd.pizzadeliveryservice.exception.PizzaNotFoundException;

@ControllerAdvice
//@EnableWebMvc
public class GlobalExceptionHandler {
	
	@ResponseStatus(value= HttpStatus.NOT_FOUND)
	@ExceptionHandler(PizzaNotFoundException.class)
	//@ResponseBody
	public ModelAndView exceptionHandler(Exception exception, HttpServletRequest request){
		ModelAndView model = new ModelAndView("error");
		model.addObject("ex", exception);
		model.addObject("url", request.getRequestURI());
		return model;
	}
	/*public String exceptionHandler(Exception exception, HttpServletRequest request){
		return exception.toString();
	}*/

}
