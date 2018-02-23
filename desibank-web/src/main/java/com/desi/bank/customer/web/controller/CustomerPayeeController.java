package com.desi.bank.customer.web.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.desi.bank.constant.DesiBankNavigationConstant;
import com.desi.bank.customer.service.CustomerPayeeService;
import com.desi.bank.customer.service.CustomerService;
import com.desi.bank.customer.web.controller.form.AddPayee;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.customer.web.controller.form.PayeeModel;
import com.desi.bank.email.service.MailService;
import com.spring.model.UserSessionVO;

@Controller
public class CustomerPayeeController {
	
	@Autowired
	@Qualifier("CustomerServiceImpl")
	public CustomerService customerService;
	
	@Autowired
	private CustomerPayeeService customerPayeeService;
	
	@Autowired
	@Qualifier("mailServiceImpl")
	public MailService mailServiceImpl;
	
	@RequestMapping(value = "customer/finishConfirm.htm", method = RequestMethod.POST)
	public String finishConfirmPayee(HttpSession session, Model model) {
		//((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid();
		//System.out.println(((PayeeInfo)session.getAttribute("payee")).toString());
		PayeeModel payee = (PayeeModel) session.getAttribute("payee");
		payee.setStatus("APPROVED");
		Timestamp timestamp=new Timestamp(new Date().getTime());
		payee.setDoe(timestamp);
		payee.setDom(timestamp);
		customerPayeeService.addPayee(payee);
		model.addAttribute("message","Payee has been added successfuly , you can transfer money to him after 30 minutes from now.");
		List<PayeeModel> payeeList = customerPayeeService.getPayeeList(((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid());
		model.addAttribute("activePayeeList", payeeList);	
		return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_PAYEE_LIST_PAGE;
	}
	
	@RequestMapping(value = "customer/active-payees.htm", method = RequestMethod.GET)
	public String allRegisteredPayee(HttpSession session, HttpServletRequest request ) {
		List<PayeeModel> payeeList = customerPayeeService.getPayeeList(((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid());
		request.setAttribute("activePayeeList", payeeList);	
		return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_PAYEE_LIST_PAGE;
		//Pageable p;
	}
	
	@RequestMapping(value = "customer/addPayee.htm", method = RequestMethod.GET)  
	public String addPayee(HttpSession session,Model model) {
		UserSessionVO userSessionVO= (UserSessionVO) session.getAttribute("userSessionVO");
		String userid=userSessionVO.getLoginid();
		String email = customerService.findEmailByUserid(userid);
		AddPayee addPayee=new AddPayee();
		addPayee.setEmail(email);
		model.addAttribute("addpayee", addPayee);
		 return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_ADD_PAYEE_PAGE;
	}
	
	@RequestMapping(value = "customer/confirmPayee.htm", method = RequestMethod.POST)
	public String confirmPayee(@Valid @ModelAttribute("addpayee") AddPayee addpayee,BindingResult result,Model model,HttpSession session ) { 
			if (result.hasErrors()) {
				return "addPayee";	
			}	
			PayeeModel payee = new PayeeModel();
			payee.setCustomerId(((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid());
			payee.setPayeeAccountNo(addpayee.getPayeeAccountNo());
			payee.setPayeeName(addpayee.getPayeeName());
			payee.setPayeeNickName(addpayee.getPayeeNickName());
			Random rnd = new Random();
			int random = 100000 + rnd.nextInt(900000);
			System.out.println(")@)@)@)random @)@   = "+random);
			payee.setUrn(random);
			payee.setRemarks("NA");
			payee.setStatus("PENDING");
			mailServiceImpl.sendMail("DesiBank", addpayee.getEmail(),"Add payee submission number", "Confirmation code:"+random);
			model.addAttribute("addPayeeInfo", payee);
			model.addAttribute("code",random);
			session.setAttribute("payee", payee);
			//return "confirmPayee";
			 return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_CONFIRM_PAYEE_PAGE;
			//return "addPayee";
	}

}
