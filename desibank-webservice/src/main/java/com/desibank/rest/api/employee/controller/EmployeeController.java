package com.desibank.rest.api.employee.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desi.bank.rest.api.vo.ApplicationStatusMessage;
import com.desi.bank.rest.api.vo.CustomerSavingVO;
import com.desi.bank.rest.api.vo.CustomerSavingVOList;

@Controller
@RequestMapping("/v1")
public class EmployeeController {
	
	
	/**
	 * Initiate Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(EmployeeController.class);
	
	
	public EmployeeController(){
		System.out.println("_)@)@)@HHHHHHHHHHHHHHH");
		if(logger.isInfoEnabled()) {
				logger.info("EmployeeController is instantiated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!............");
				logger.info("EmployeeController is instantiated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!............");
				logger.info("EmployeeController is instantiated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!............");
				logger.info("EmployeeController is instantiated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!............");
		}
	}
	
	
	@RequestMapping(value="/customers/savings/xml",method=RequestMethod.GET,produces={"application/xml"})
	@ResponseBody public CustomerSavingVOList  findCustomerSavingEnquiryXML() {
		CustomerSavingVO customerSavingVO=new CustomerSavingVO();
		customerSavingVO.setCsaid(202);
		customerSavingVO.setDoa(new Date());
		customerSavingVO.setEmail("nagen@gmail.com");
		customerSavingVO.setLocation("Green Field!");
		customerSavingVO.setMobile("+1202928222");
		customerSavingVO.setName("Nagendra Kumar");
		customerSavingVO.setStatus("PENDING");
		
		CustomerSavingVO customerSavingVO1=new CustomerSavingVO();
		customerSavingVO1.setCsaid(302);
		customerSavingVO1.setDoa(new Date());
		customerSavingVO1.setEmail("michal@gmail.com");
		customerSavingVO1.setLocation("Fremont CA100!");
		customerSavingVO1.setMobile("+32030340");
		customerSavingVO1.setName("Michal Kumar");
		customerSavingVO1.setStatus("PENDING");
		
		List<CustomerSavingVO> customerSavingVOs=new ArrayList<CustomerSavingVO>();
		customerSavingVOs.add(customerSavingVO);
		customerSavingVOs.add(customerSavingVO1);
		
		CustomerSavingVOList customerSavingVOList=new CustomerSavingVOList();
		customerSavingVOList.setCustomerSavingVOs(customerSavingVOs);
		
		return customerSavingVOList;
	}
	
	
	
	@RequestMapping(value="/customers/savings",method=RequestMethod.GET,produces={"application/json"})
	@ResponseBody public CustomerSavingVOList  findCustomerSavingEnquiry(@RequestHeader("User-Agent") String userAgent) {
		
		if(logger.isInfoEnabled()) {
			logger.info("executing   the method findCustomerSavingEnquiry................");
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("userAgent .... "+userAgent);
		}
		
		CustomerSavingVO customerSavingVO=new CustomerSavingVO();
		customerSavingVO.setCsaid(202);
		customerSavingVO.setDoa(new Date());
		customerSavingVO.setEmail("nagen@gmail.com");
		customerSavingVO.setLocation("Green Field!");
		customerSavingVO.setMobile("+1202928222");
		customerSavingVO.setName("Nagendra Kumar");
		customerSavingVO.setStatus("PENDING");
		
		CustomerSavingVO customerSavingVO1=new CustomerSavingVO();
		customerSavingVO1.setCsaid(302);
		customerSavingVO1.setDoa(new Date());
		customerSavingVO1.setEmail("michal@gmail.com");
		customerSavingVO1.setLocation("Fremont CA100!");
		customerSavingVO1.setMobile("+32030340");
		customerSavingVO1.setName("Michal Kumar");
		customerSavingVO1.setStatus("PENDING");
		
		List<CustomerSavingVO> customerSavingVOs=new ArrayList<CustomerSavingVO>();
		customerSavingVOs.add(customerSavingVO);
		customerSavingVOs.add(customerSavingVO1);
		
		CustomerSavingVOList customerSavingVOList=new CustomerSavingVOList();
		customerSavingVOList.setCustomerSavingVOs(customerSavingVOs);
		if(logger.isDebugEnabled()) {
			logger.debug(customerSavingVOList);
		}
		return customerSavingVOList;
	}
	
	@RequestMapping(value="/customers/savings",method=RequestMethod.POST,consumes="application/json")
	@ResponseBody public ApplicationStatusMessage  customerSavingEnquiryPost(@RequestBody 
			CustomerSavingVO customerSavingVO) {
			System.out.println("____________________");
			System.out.println(customerSavingVO);
			System.out.println(customerSavingVO);
			System.out.println(customerSavingVO);
			System.out.println(customerSavingVO);
			System.out.println(customerSavingVO);
			System.out.println("____________________");
			ApplicationStatusMessage applicationStatusMessage=new ApplicationStatusMessage();
			applicationStatusMessage.setMessage("customerSavingVO is uploaded successfully");
			applicationStatusMessage.setStatus("success");
			return applicationStatusMessage;
	}
	
	@RequestMapping(value="/customers/savings/{csid}",method=RequestMethod.DELETE)
	@ResponseBody public ApplicationStatusMessage  customerSavingEnquiryPost(@PathVariable("csid") String csid) {
			System.out.println("____________________");
			System.out.println("csid = "+csid);
			System.out.println("csid = "+csid);
			System.out.println("csid = "+csid);
			System.out.println("csid = "+csid);
			System.out.println("csid = "+csid);
			System.out.println("____________________");
			ApplicationStatusMessage applicationStatusMessage=new ApplicationStatusMessage();
			applicationStatusMessage.setMessage("customerSavingVO is delete successfully with ..."+csid);
			applicationStatusMessage.setStatus("success");
			return applicationStatusMessage;
	}
	
	@RequestMapping(value="/customers/savings",method=RequestMethod.PUT,consumes="application/json")
	@ResponseBody public ApplicationStatusMessage  customerSavingEnquiryPut(@RequestBody CustomerSavingVO customerSavingVO) {
			System.out.println("____________________");
			System.out.println(customerSavingVO);
			System.out.println(customerSavingVO);
			System.out.println(customerSavingVO);
			System.out.println(customerSavingVO);
			System.out.println(customerSavingVO);
			System.out.println("____________________");
			ApplicationStatusMessage applicationStatusMessage=new ApplicationStatusMessage();
			applicationStatusMessage.setMessage("customerSavingVO is update successfully");
			applicationStatusMessage.setStatus("success");
			return applicationStatusMessage;
	}
}
