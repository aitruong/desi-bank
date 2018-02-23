package com.desi.bank.customer.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.desi.bank.common.dao.entity.PayeeInfo;
import com.desi.bank.constant.DesiBankNavigationConstant;
import com.desi.bank.customer.service.CustomerPayeeService;
import com.desi.bank.customer.service.CustomerService;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.customer.web.controller.form.PayeeModel;
import com.spring.model.UserSessionVO;

@Controller
public class CustomerPayeeController {
	
	@Autowired
	@Qualifier("CustomerServiceImpl")
	public CustomerService customerService;
	
	@Autowired
	private CustomerPayeeService customerPayeeService;
	
	@RequestMapping(value = "customer/finishConfirm.htm", method = RequestMethod.POST)
	public String finishConfirmPayee(HttpSession session, Model model) {
		//((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid();
		//System.out.println(((PayeeInfo)session.getAttribute("payee")).toString());
		PayeeModel payee = (PayeeModel) session.getAttribute("payee");
		customerPayeeService.addPayee(payee);
		UserSessionVO userSessionVO= (UserSessionVO) session.getAttribute("userSessionVO");
		String loginid=userSessionVO.getLoginid();
		CustomerForm customerdetail= (CustomerForm) customerService.getUserDetail(loginid);
		model.addAttribute("detail",customerdetail);
		model.addAttribute("message","Payee added!");
		return "customer";
	}
	
	@RequestMapping(value = "customer/active-payees.htm", method = RequestMethod.GET)
	public String allRegisteredPayee(HttpSession session, HttpServletRequest request ) {
		List<PayeeModel> payeeList = customerPayeeService.getPayeeList(((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid());
		request.setAttribute("activePayeeList", payeeList);	
		return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_PAYEE_LIST_PAGE;
		//Pageable p;
	}

}
