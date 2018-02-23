package com.desi.bank.customer.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.desi.bank.customer.service.CustomerService;
import com.desi.bank.email.service.MailService;

@RunWith(SpringJUnit4ClassRunner.class)
//This should scan only controller package
@ContextConfiguration({ "classpath*:test-spring-mvc-web-controller.xml"})
public class TesTest {
	
	@Mock
	private  MailService mailServiceImpl;
	
	@Mock
	private CustomerService customerService;
	
	
	//This is creating an instance of CustomerController and injecting all the mock services inside 
	@InjectMocks
	private CustomerController customerController;
	
	
	private MockMvc mockMvc;
	
	@Before
    public void setup() {
			//this is used to implement mocking stub
			MockitoAnnotations.initMocks(this);
			//Creating mock spring web container for web application
			mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
	
	@Test
	public void tictic(){
		
	}

}
