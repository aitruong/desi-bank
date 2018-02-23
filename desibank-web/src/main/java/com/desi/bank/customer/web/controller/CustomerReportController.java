package com.desi.bank.customer.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.desi.bank.customer.service.CustomerService;
import com.desi.bank.customer.web.controller.form.CustomerForm;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class CustomerReportController {
	

	/**
	 * Initiate Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(CustomerReportController.class);
	
	@Autowired
	@Qualifier("CustomerServiceImpl")
	public CustomerService customerService;
	
	@RequestMapping(value="xls/customerReports.xls",method=RequestMethod.GET)
	public String showCustomerDetailsReportxls(Model model,HttpServletResponse response){
		List<CustomerForm> customerForms=customerService.findCustomers();
		JRDataSource  jrDataSource=new JRBeanCollectionDataSource(customerForms);
		model.addAttribute("jasperDataSource",jrDataSource);
		return "xlsReport";
	}
	
	@RequestMapping(value="pdf/customerReports",method=RequestMethod.GET)
	public String showCustomerDetailsReport(Model model){
		List<CustomerForm> customerForms=customerService.findCustomers();
		JRDataSource  jrDataSource=new JRBeanCollectionDataSource(customerForms);
		model.addAttribute("jasperDataSource",jrDataSource);
		return "pdfReport";
	}
	
	@RequestMapping(value="html/customerReports",method=RequestMethod.GET)
	public String showCustomerDetailsReporthtml(Model model){
		List<CustomerForm> customerForms=customerService.findCustomers();
		JRDataSource  jrDataSource=new JRBeanCollectionDataSource(customerForms);
		model.addAttribute("jasperDataSource",jrDataSource);
		return "htmlReport";
	}

}
