package com.desi.bank.admin.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.desi.bank.admin.service.AdminService;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.data.util.Encrypter;
import com.spring.model.UserSessionVO;

@Controller
public class AdminController {
	
	@Autowired
	@Qualifier("adminServiceImpl")
	public AdminService adminService;
	
	@RequestMapping(value = "/admin/showUnapprovedCustomers.htm", method=RequestMethod.GET)
	public String unapprovedCustomers(Model model) {
		
		List<CustomerForm> customerForms = adminService.listUnapprovedCustomers();
		model.addAttribute("customerForms",customerForms);
		
		return "unapprovedCustomers";
	}
	
	
	@RequestMapping(value = "/admin/searchUnapprovedCustomer.htm", method=RequestMethod.POST)
	public String searchUnapprovedCustomer(Model model, HttpServletRequest request){
		String keyword= request.getParameter("keyword");
		List<CustomerForm> customerForms = adminService.searchUnapprovedCustomers(keyword);
		model.addAttribute("customerForms",customerForms);
		return "unapprovedCustomers";
	}
	
	@RequestMapping(value="/admin/approvedCustomers.htm", method=RequestMethod.POST)
	public String approveCustomers(HttpServletRequest request,Model model){
		
		String[] approveCustomerIds=request.getParameterValues("approveCustomer");
		//database query
		adminService.approveCustomers(approveCustomerIds);
		List<CustomerForm> customerForms = adminService.listUnapprovedCustomers();
		model.addAttribute("customerForms",customerForms);
		return "unapprovedCustomers";
	}
	
	@RequestMapping(value = "/admin/showLockedCustomers.htm", method=RequestMethod.GET)
	public String showLockedCustomers(@RequestParam(value = "page", defaultValue="1", required=false) int page,Model model){
		System.out.println("page is"+page);
		List<CustomerForm> customerForms = adminService.listPaginatedCustomers(page);
		model.addAttribute("customerForms",customerForms);
		return "lockedCustomer";
	}
	
	
	@RequestMapping(value="/admin/lockedCustomers.htm", method=RequestMethod.POST)
	public String showLockedCustomers(HttpServletRequest request,Model model){
		String[] approveCustomerIds=request.getParameterValues("approveCustomer");
		//database query
		adminService.lockCustomers(approveCustomerIds);
		List<CustomerForm> customerForms = adminService.listUnlockedCustomers();
		model.addAttribute("customerForms",customerForms);
		return "lockedCustomer";
	}
	
	@RequestMapping(value = "/admin/showUnLockedCustomers.htm", method=RequestMethod.GET)
	public String showUnLockedCustomers(Model model){
		List<CustomerForm> customerForms = adminService.listlockedCustomers();
		model.addAttribute("customerForms",customerForms);
		return "unLockedCustomers";
	}
	
	
	@RequestMapping(value="/admin/unlockedCustomers.htm", method=RequestMethod.POST)
	public String showUnLockedCustomers(HttpServletRequest request,Model model){
		String[] approveCustomerIds=request.getParameterValues("approveCustomer");
		//database query
		adminService.unlockCustomers(approveCustomerIds);
		List<CustomerForm> customerForms = adminService.listlockedCustomers();
		model.addAttribute("customerForms",customerForms);
		return "unLockedCustomers";
	}
	
	@RequestMapping(value = "/admin/showCustomers.htm", method=RequestMethod.GET)
	public String showCustomers(Model model){
		List<CustomerForm> customerForms = adminService.showCustomers();
		model.addAttribute("customerForms",customerForms);
		return "showCustomers";
	}
	
	/*@RequestMapping(value="/admin/excelExport.htm", method=RequestMethod.GET)
	public ModelAndView excelExport(Model model,  HSSFWorkbook workbook){
		List<CustomerForm> customerForms = adminService.showCustomers();
        return new ModelAndView("excelView","customerForms",customerForms);
    }*/

	
	
	@RequestMapping(value = "/admin/changePassword.htm", method=RequestMethod.GET)
	public String changePassword(){
		
		//List<CustomerForm> customerForms = adminService.showCustomers();
		//model.addAttribute("customerForms",customerForms);
		
		return "changePassword";
	}
	
	@RequestMapping(value = "/admin/changePassword.htm", method=RequestMethod.POST)
	public String changedPassword(@RequestParam(value = "password", required = false) String password,HttpSession session){
		System.out.println(password);
		String userid = ((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid();
		Encrypter encrypter=new Encrypter();
		adminService.changePasword(userid, encrypter.encrypt(password));
		//List<CustomerForm> customerForms = adminService.showCustomers();
		//model.addAttribute("customerForms",customerForms);
		
		return "changePassword";
	}
	

}
