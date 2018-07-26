package com.desi.bank.rest.api.employee;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.desi.bank.customer.service.CustomerService;
import com.desi.bank.customer.web.controller.form.CustomerAccountInfoVO;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.employee.service.EmployeeService;
import com.desi.bank.employee.web.controller.form.ApplicationMessageResponse;
import com.desi.bank.employee.web.controller.form.CustomerAccountRegistrationVO;
import com.desi.bank.employee.web.controller.form.CustomerAccoutRequestVO;

/**
 * 
 * @author nagendra
 *
 */
@Service
@Path("/employee")
public class EmployeeRestApi {
	
	@Autowired
	@Qualifier("EmployeeServiceImpl")
	private EmployeeService employeeService;
	
	@Autowired
	@Qualifier("CustomerServiceImpl")
	private CustomerService customerService;
	
	
	
	@Path("/create-customer-account")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ApplicationMessageResponse createCustomerAccount(CustomerAccoutRequestVO customerAccoutRequestVO){
		 CustomerAccountInfoVO customerAccountInfoVO=employeeService.createCustomerAccount(customerAccoutRequestVO.getUserid());
		
		 CustomerForm customerForm=customerService.getCustomer(customerAccoutRequestVO.getUserid());
		//write code to send email
		CustomerAccountRegistrationVO customerAccount=new CustomerAccountRegistrationVO();
		//Prepare input data
		customerAccount.setAccount(customerAccountInfoVO.getAccountNumber());
		customerAccount.setAppRef("TempA3030393");
		customerAccount.setAvgbalance("1000");
		customerAccount.setBankEmail("admin@desibank.com");
		customerAccount.setBranch(customerAccountInfoVO.getBranch());
		customerAccount.setCurrency(customerAccountInfoVO.getCurrency());
		customerAccount.setImage("http://localhost:8080/desibank-web/images/thanks.png");
		customerAccount.setTo(customerForm.getEmail());
		customerAccount.setName(customerForm.getName());
		customerAccount.setSubject("Regarding account summary for your new "+customerAccountInfoVO.getAccountType()+" account.");
		customerAccount.setType(customerAccountInfoVO.getAccountType());
		customerAccount.setUserid(customerAccoutRequestVO.getUserid());
		
		employeeService.sendAccountSummaryEmail(customerAccount);
		ApplicationMessageResponse applicationMessageResponse=new ApplicationMessageResponse();
		applicationMessageResponse.setStatus("success");
		applicationMessageResponse.setMessage("Account is created successfully for user id "+customerAccoutRequestVO.getUserid()+" and generated account number is 08383736736363");
		//applicationMessageResponse.setMessage("Account is created successfully for user id "+customerAccoutRequestVO.getUserid()+" and generated account number is "+customerAccountInfoVO.getAccountNumber());
		return applicationMessageResponse;
	}

}
