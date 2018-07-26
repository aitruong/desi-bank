package com.desi.bank.rest.api.customer;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.desi.bank.constant.DesiBankConstant;
import com.desi.bank.customer.service.CustomerService;
import com.desi.bank.customer.web.controller.form.CustomerSavingForm;
import com.desi.bank.employee.web.controller.form.ApplicationMessageResponse;


@Component
@Path("/customer")
public class BankCustomerRestApi {
	
	
	@Autowired
	@Qualifier("CustomerServiceImpl")
	public CustomerService customerService;
	
	//web.xml - v1
	// $.getJSON(context+"/v1/customer/find/age?pdate=sdate",function(data){
	@Path("/find/age")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ApplicationMessageResponse calculateAge(@QueryParam("pdate") String pdate){
			////{"age" : 10}
			 //pdate =2018-07-19
			String tokens[]=pdate.split("-");
			int dobyear=Integer.parseInt(tokens[0]);
			int year = Calendar.getInstance().get(Calendar.YEAR);
			int finalAge=year-dobyear;
			if(finalAge<0){
				finalAge=0;
			}
			ApplicationMessageResponse applicationMessageResponse=new ApplicationMessageResponse();
			applicationMessageResponse.setStatus(DesiBankConstant.SUCCESS);
			applicationMessageResponse.setMessage(finalAge+"");
			return applicationMessageResponse;
	}
	
	
	@Path("/savingRequest")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ApplicationMessageResponse rejectSavingSavingAccountRequest(CustomerSavingForm customerSavingForm){
			System.out.println(customerSavingForm);
			customerService.savingAccountRequest(customerSavingForm);
			ApplicationMessageResponse applicationMessageResponse=new ApplicationMessageResponse();
			applicationMessageResponse.setStatus(DesiBankConstant.SUCCESS);
			 return applicationMessageResponse;
	}
	
	@Path("/temp")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public CustomerSavingForm showTemp(){
		System.out.println("_@_magic000000000000000");
		CustomerSavingForm customerSavingForm2=new CustomerSavingForm();
		customerSavingForm2.setEmail("nagen@gmail.com");
		customerSavingForm2.setLocation("India");
		customerSavingForm2.setMobile("+202929292");
		customerSavingForm2.setName("Nagendra!");
		return customerSavingForm2;
	}

}
