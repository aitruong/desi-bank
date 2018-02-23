package com.desi.bank.customer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//This is acting as a controller/action/model
//http://localhost:9974/SpringWeb/hello.htm?method=GET

@Controller
public class HelloSpringContoller {

	@RequestMapping(value="hello.htm",method=RequestMethod.GET)
	public String foo() {
	    System.out.println("_______________Spring Welcomes you_________________");
	    return "hello";	//ViewResolver
	}
}
