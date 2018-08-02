package com.desi.bank.scheduler.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.desi.bank.customer.service.CustomerService;
import com.desi.bank.customer.web.controller.form.CustomerForm;

@Service
public class PasswordResetReminderScheduler {
	
	 
	 @Value("${company.email}")
	 private String companyEmail;
	 
	 @Autowired
	 @Qualifier("CustomerServiceImpl")
	 private CustomerService customerService;
	
	/* @Value("${app.base.url}")
	 private String appBaseUrl;*/
	 
	    //0 0 12 * * ?	- every day 12 PM 
	    //0 0 0,12 * * ?
		@Scheduled(cron = "*/60 * * * * ?")
		public void sendPasswordResetReminders() {
			System.out.println("current date = "+new Date());
			System.out.println(")#@)#)#)#)#)#)This is called everyone minutes");
			System.out.println(")#@)#)#)#)#)#)This is called everyone minutes");
			System.out.println("appBaseUrl = "+companyEmail);
			System.out.println(")#@)#)#)#)#)#)This is called everyone minutes");
			System.out.println(")#@)#)#)#)#)#)This is called everyone minutes");
			List<CustomerForm> customerForms=customerService.findCustomersExpirePassWithInSevenDays();
			System.out.println(customerForms);
			
		}

}
