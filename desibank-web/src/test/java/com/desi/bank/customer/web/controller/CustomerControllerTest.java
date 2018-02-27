/*package com.desi.bank.customer.web.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.desi.bank.customer.service.CustomerService;
import com.desi.bank.customer.web.controller.form.CustomerAccountInfoVO;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.email.service.MailService;
import com.spring.model.UserSessionVO;


@RunWith(SpringJUnit4ClassRunner.class)
//This should scan only controller package
@ContextConfiguration({ "classpath*:test-spring-mvc-web-controller.xml"})
public class CustomerControllerTest {
	
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
	
	@RequestMapping(value = "allCustomers.htm", method = RequestMethod.GET)
	public String allCustomers(Model model) {
		 List<CustomerForm> customerForms=customerService.findCustomers();
		 model.addAttribute("customerForms",customerForms);
		return "customers";
	}

	@Test
	public void testAllCustomers() {
		//here we are stubbing for bankPayeeService for method findAllPayeesByEmail
		CustomerForm customer1=mock(CustomerForm.class);
		customer1.setUserid("alexalex");
		customer1.setAddress("CA");
		customer1.setAge(23);
		customer1.setAnswer1("mxkxk");
		customer1.setAnswer2("Oaiueui");
		customer1.setApproved("yes");
		customer1.setEmail("alex@gmail.com");
		customer1.setJobTitle("Manager");
		customer1.setMobile("9283773733");
		customer1.setName("Alex Singh");
		customer1.setPassword("test@31");
		customer1.setQuestion1("what is your pet name?");
		customer1.setQuestion2("What is your mother name");
		customer1.setSsn("897-23-9363");
		
		CustomerForm customer2=mock(CustomerForm.class);
		customer2.setUserid("yadna01");
		customer2.setAddress("Fremont");
		customer2.setAge(23);
		customer2.setAnswer1("jack");
		customer2.setAnswer2("varanasi");
		customer2.setApproved("yes");
		customer2.setEmail("nagen@gmail.com");
		customer2.setJobTitle("Manager");
		customer2.setMobile("+102-228228922");
		customer2.setName("Nagendra Kumar");
		customer2.setPassword("root@123");
		customer2.setQuestion1("what is your pet name?");
		customer2.setQuestion2("city where you born?");
		customer2.setSsn("212-34-1233");
		 List<CustomerForm> customerFormsList =new ArrayList<>(); // type inference
		 customerFormsList.add(customer1);
		 customerFormsList.add(customer2);
		 //stubbing the service layer behavior.........................
		when(customerService.findCustomers()).thenReturn(customerFormsList);
		try {
			mockMvc.perform(get("/allCustomers.htm").contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk())
			.andExpect(model().attribute("customerForms", customerFormsList)).andExpect(view().name("customers"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	@Ignore
	public void testDummy() {
	
	}
	
	@Test
	public void testCustomerAccountSummary() {
		
		//Mocking session to fetch user logged in data for the user
		//HttpSession peoerieuie = mock(HttpSession.class);
		MockHttpSession psession = mock(MockHttpSession.class);
		//MockHttpServletRequest request = mock(MockHttpServletRequest.class);
		UserSessionVO userSessionVO=new UserSessionVO();
		userSessionVO.setApproved("yes");
		userSessionVO.setLocked("no");
		userSessionVO.setLoginid("alex");
		userSessionVO.setName("Ajay Kumar");
		userSessionVO.setPassword("test");
		userSessionVO.setRole("customer");
		//here we are setting userSessionVO into session scope
		when(psession.getAttribute("userSessionVO")).thenReturn(userSessionVO);
		
		//Here mocking the service layer to fetch user account summary as per user name for logged in user
		CustomerAccountInfoVO accountInfoVO=new CustomerAccountInfoVO();
		accountInfoVO.setAccountNumber("51231827263");
		accountInfoVO.setAccountType("Saving");
		accountInfoVO.setAvBalance(303003);
		accountInfoVO.setCurrency("$");
		accountInfoVO.setCustomerId("alex");
		accountInfoVO.setId(100);
		accountInfoVO.setPayeeName("Alexa");
		accountInfoVO.setStatusAsOf(new Date());
		accountInfoVO.setTavBalance(303003);
		 List<CustomerAccountInfoVO> customerAccountList=new ArrayList<>();
		 customerAccountList.add(accountInfoVO);
		when(customerService.getAccountDetails(userSessionVO.getLoginid())).thenReturn(customerAccountList);
		
		try {
			mockMvc.perform(get("/customer/showCustomerAccountsSummary.htm").session(psession).contentType(MediaType.APPLICATION_FORM_URLENCODED)).andExpect(status().isOk())
			.andExpect(model().attribute("customerAccountList", customerAccountList)).andExpect(view().name("customer/account-summary"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@RequestMapping(value = "/customer/showCustomerAccountsSummary.htm", method = RequestMethod.GET)
	public String customerAccountSummary(Model model, HttpSession session ) {
		String userid = ((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid();
		 List<CustomerAccountInfoVO> customerAccountList=customerService.getAccountDetails(userid);
		 model.addAttribute("customerAccountList",customerAccountList);
		 return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_ACCOUNT_SUMMARY_PAGE;
	}

}
*/