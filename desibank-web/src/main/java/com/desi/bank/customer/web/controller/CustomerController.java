package com.desi.bank.customer.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailSendException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import com.desi.bank.common.dao.entity.PayeeInfo;
import com.desi.bank.common.dao.entity.SecurityQuestions;
import com.desi.bank.constant.DesiBankNavigationConstant;
import com.desi.bank.constant.SavingApplicationStatus;
import com.desi.bank.customer.service.CustomerPayeeService;
import com.desi.bank.customer.service.CustomerService;
import com.desi.bank.customer.web.controller.form.AddPayee;
import com.desi.bank.customer.web.controller.form.CustomerAccountInfoVO;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.customer.web.controller.form.CustomerSavingForm;
import com.desi.bank.customer.web.controller.form.MiniStatementVO;
import com.desi.bank.customer.web.controller.form.PayeeModel;
import com.desi.bank.data.util.DesiBankUtils;
import com.desi.bank.email.service.ICustomerEmailService;
import com.desi.bank.email.service.MailService;
import com.desi.bank.employee.web.controller.form.RegistrationLinksForm;
import com.desi.bank.exception.DesiBankException;
import com.spring.model.UserSessionVO;



/**
 * 
 * @author nagendra
 *   This class is used to handle all the request coming from 
 *   the customer
 */
@Controller
public class CustomerController {
	
	/**
	 * Initiate Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(CustomerController.class);
	
	@Autowired
	private CustomerPayeeService customerPayeeService;
	
	@Autowired
	@Qualifier("bcryptEncoder")
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	@Qualifier("mailServiceImpl")
	public MailService mailServiceImpl;
	
	@Autowired
	@Qualifier("CustomerEmailService")
	public ICustomerEmailService customerEmailService;
	
	
	
	
	
	@Autowired
	@Qualifier("CustomerServiceImpl")
	public CustomerService customerService;
	
	public CustomerController(){
		System.out.println("@))*******************^^^^^^^^^^^^^^^%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("@))*******************^^^^^^^^^^^^^^^%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("@))*******************^^^^^^^^^^^^^^^%%%%%%%%%%%%%%%%%%%%%%%%%");
		System.out.println("@))*******************^^^^^^^^^^^^^^^%%%%%%%%%%%%%%%%%%%%%%%%%");
		
	}
	
	
	//Model - it is used to carry the data from the conctroller to the view
		@RequestMapping(value = "/customer/tregistation.htm", method = RequestMethod.GET)
		public String tcustomerRegistration(@ModelAttribute  CustomerSavingForm customerSavingForm,Model model) {
			 return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_REGISTRATION_PAGE;
		}
		
		
		@RequestMapping(value = "/saving/accounts/registration.htm", method = RequestMethod.POST)
		public String addCustomer(@ModelAttribute("customer") CustomerForm customer,ModelMap map) {
			System.out.println(customer);
			customer.setApproved("0");
		     //customer.setLocked("yes");
		     customer.setAccountNum("NA");
		     StringBuilder sb= new StringBuilder();
		        sb.append("Welcome to Desi Bank");
		        sb.append("\n\n User id is " + customer.getUserid());
		        sb.append("\n\n Password is " + customer.getPassword());

		        //Password is encrypted before persisting in database
		     customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		     try{
		    	try { 
		    		customerService.persistCustomer(customer);
		    	}catch (DesiBankException  e) {
		    		if(logger.isErrorEnabled()){
		    			logger.error(e.getMessage());
		    			logger.error(e.getCause());
		    		}
		    		e.printStackTrace();
		    		return "adminError";
				}
		       /* StringBuilder sb= new StringBuilder();
		        sb.append("Welcome to Desi Bank");
		        sb.append("\n\n User id is " + customer.getUserid());
		        sb.append("\n\n Password is " + customer.getPassword());*/
		        mailServiceImpl.sendMail("DesiBank", customer.getEmail(),"Registration Successfull", sb.toString());
		     }
		     catch (MailSendException e) {
		    	 if(logger.isErrorEnabled()){
		    			logger.error(e);
		    		}
		    	 map.addAttribute("emailMessage", "Email could be sent since you are not connected with internet!");
		     }
		    map.addAttribute("msg", "You have registered Successfully!");
			 return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_REGISTRATION_PAGE;
				
		}
		
	//Model - it is used to carry the data from the conctroller to the view
		@RequestMapping(value = "/saving/accounts/registration", method = RequestMethod.GET)
		public String customerRegistration(@RequestParam("cuid") String cuid,Model model) {
					  if(logger.isDebugEnabled()) {
						  logger.debug("cuid coming from customer registration  = "+cuid);
					  }
					  RegistrationLinksForm registrationLinksForm=customerService.findLinkDetailByCuid(cuid);
					  if(registrationLinksForm.getLno()==0) { //if link does not exist into the database
						  
					  }else{
						  	//logic to check weather link is expired or not
						  	
					  }
					  CustomerSavingForm customerSavingForm=customerService. findCustomerSavingEnquiryByEmail(registrationLinksForm.getEmail());
					  model.addAttribute("customerSavingForm", customerSavingForm);
					  CustomerForm customerForm=new CustomerForm();
					  model.addAttribute("customerForm", customerForm);
					  //We are fetching all the security questions
					  List<SecurityQuestions> securityQuestions1=customerService.securityQns();
					  model.addAttribute("securityQuestions1", securityQuestions1);
					  
					  List<SecurityQuestions> securityQuestions2=customerService.securityQns2();
					  model.addAttribute("securityQuestions2", securityQuestions2);
					  
					  
					 return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_REGISTRATION_PAGE;
				}
		
		//Model - it is used to carry the data from the conctroller to the view
		/*@RequestMapping(value = "/customer/accountApproval.htm", method = RequestMethod.POST)
		public String accountApproval(@ModelAttribute  CustomerSavingForm customerSavingForm,Model model) {
			
		}*/
		
		//Model - it is used to carry the data from the conctroller to the view
		@RequestMapping(value = "/customer/savingAccountWithAjax.htm", method = RequestMethod.POST)
		@ResponseBody	public String savingAccountEnquiry(@ModelAttribute  CustomerSavingForm customerSavingForm,Model model,HttpServletRequest request) {
			if(logger.isDebugEnabled()) {
				logger.debug(customerSavingForm);
			}
			//you should check  customerSavingForm.getEmail.
			//this email exist...in database..............................
			//customer_saving_enquiry_tbl 
			//findEmailCustomerSavingRequest(String email);
			//return "redirect:/index.jsp?message=your application has been submitted sucessfully....";
			
			CustomerSavingForm dCustomerSavingForm=customerService.findCustomerSavingEnquiryByEmail(customerSavingForm.getEmail());
			if(dCustomerSavingForm!=null && dCustomerSavingForm.getEmail()!=null){
				 return "exist";
			}
			
			customerSavingForm.setDoa(new Date());
			customerSavingForm.setStatus(SavingApplicationStatus.PENDING_STATUS);
			customerService.savingAccountRequest(customerSavingForm);
			String imageUrl=DesiBankUtils.getServerBaseURL(request)+"/images/regards.png";
			//make this sendEnquiryConfirmation asynchronous
			customerEmailService.sendEnquiryConfirmation(customerSavingForm.getEmail(),customerSavingForm.getName(), imageUrl);
			return "success";  // /WEB-INF/jsp/common/success
		}
	
	//Model - it is used to carry the data from the conctroller to the view
/*	@RequestMapping(value = "/customer/savingAccount.htm", method = RequestMethod.POST)
	public String addCustomer(@ModelAttribute  CustomerSavingForm customerSavingForm,Model model,HttpServletRequest request) {
		if(logger.isDebugEnabled()) {
			logger.debug(customerSavingForm);
		}
		
		//you should check  customerSavingForm.getEmail.
		//this email exist...in database..............................
		//customer_saving_enquiry_tbl 
		//findEmailCustomerSavingRequest(String email);
		//return "redirect:/index.jsp?message=your application has been submitted sucessfully....";
		
		customerSavingForm.setDoa(new Date());
		customerSavingForm.setStatus(SavingApplicationStatus.PENDING_STATUS);
		customerService.savingAccountRequest(customerSavingForm);
	
		//Here write code for sending email using the template
		MimeMessage message = mailSender.createMimeMessage();
		try {
			InternetAddress fromAddress = new InternetAddress(	"nagen@synergistic", "Aaron");
			message.setFrom(fromAddress);
			// message.setSender(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(customerSavingForm.getEmail()));
			message.setSubject("Regarding Saving Account Opening Request!");
			message.setSentDate(new Date());
			
			// This mail has 2 part, the BODY and the embedded image
			MimeMultipart multipart = new MimeMultipart("related");
			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			Template template = velocityEngine.getTemplate("./templates/saving-account-confirmation.vm");
			VelocityContext velocityContext = new VelocityContext();
			velocityContext.put("name", customerSavingForm.getName());
			Date date=new Date();
			velocityContext.put("appNo", "AS-"+date.getTime());
			StringWriter stringWriter = new StringWriter();
			template.merge(velocityContext, stringWriter);
			System.out.println(" :-"+stringWriter.toString());
	         messageBodyPart.setContent(stringWriter.toString(), "text/html");
			multipart.addBodyPart(messageBodyPart);
			
			 messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setDataHandler(new DataHandler(new URL(DesiBankUtils.getServerBaseURL(request)+"/images/regards.png")));
	         messageBodyPart.setHeader("Content-ID", "<bankimage>");
	         multipart.addBodyPart(messageBodyPart);
			// put everything together
			message.setContent(multipart);
			mailSender.send(message);
		} catch (Exception exe) {
			exe.printStackTrace();
		}
		
		return "redirect:/index.jsp?message=your application has been submitted sucessfully....";
	}*/
	
	
	/**
	 *   This method is used to display the registration page to the customer
	 *   with security questions,
	 *   
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "showCustomerRegistration.htm", method = RequestMethod.GET)
	public String register(Model model, HttpSession session,HttpServletRequest request) {
		    //CustomerForm customer=new CustomerForm();
		    //model.addAttribute("customer",customer);
		    List<SecurityQuestions> sq1= (List<SecurityQuestions>)customerService.securityQns();
		    model.addAttribute("questions", sq1);
		    List<SecurityQuestions> sq2 = (List<SecurityQuestions>)customerService.securityQns2();
		    model.addAttribute("questions2", sq2);
		    return "customerRegistration";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// to actually be able to convert Multipart instance to byte[]
		// we have to register a custom editor
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		// now Spring knows how to handle multipart object and convert them
	}

	@RequestMapping(value = "findImageByUserid", method = RequestMethod.GET)
	public void findImageByUserid(@RequestParam("userid") String userid,HttpServletResponse  response) throws IOException {
		 byte  image[]=customerService.findImageByUserid(userid);
		 response.setContentType("image/png");
		 ServletOutputStream outputStream=response.getOutputStream();
		 outputStream.write(image);
		 outputStream.flush();
		 outputStream.close();
	}
	
	
	@RequestMapping(value = "allCustomers.htm", method = RequestMethod.GET)
	public String allCustomers(Model model) {
		 List<CustomerForm> customerForms=customerService.findCustomers();
		 model.addAttribute("customerForms",customerForms);
		return "customers";
	}
	
	@RequestMapping(value = "deleteCustomer.htm", method = RequestMethod.GET)
	public String deleteCustomer(@RequestParam(value="id") int id, Model model ){
	         customerService.deleteCustomer(id);
			 List<CustomerForm> customerForms=customerService.findCustomers();
			 model.addAttribute("customerForms",customerForms);
			return "customers";
	}
	
	@RequestMapping(value = "editCustomer.htm", method = RequestMethod.GET)
	public String getCustomer(Model model, @RequestParam(value="id") int id) {
		CustomerForm customerForms = customerService.getCustomer(id);
		 model.addAttribute("customerForms",customerForms);
		return "editCustomer";
	}
	
	
	@RequestMapping(value = "editCustomer.htm", method = RequestMethod.POST)
	public String updateCustomer(@ModelAttribute("customer") CustomerForm customer, Model model,  HttpSession session) {
		  customerService.updateCustomer(customer);
		  UserSessionVO userSessionVO= (UserSessionVO) session.getAttribute("userSessionVO");
		  String loginid=userSessionVO.getLoginid();
		  CustomerForm customerdetail= (CustomerForm) customerService.getUserDetail(loginid);
		   model.addAttribute("detail",customerdetail);
		   return "customer";
		   
	}
	
	@RequestMapping(value = "/customer/showCustomerAccounts.htm", method = RequestMethod.GET)
	public String customerAccount(Model model, HttpSession session,HttpServletRequest request ) {
		String userid = ((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid();
		 List<CustomerAccountInfoVO> customerAccountList=customerService.getAccountDetails(userid);
		 if(customerAccountList.isEmpty())
		 {
			request.setAttribute("msg", "no"); 
		 }
		 else {
			request.setAttribute("msg", "yes");
		}
		 model.addAttribute("customerAccountList",customerAccountList);
		return "customerAccountInfo";
	}
	
	@RequestMapping(value = "/customer/showCustomerAccountsSummary.htm", method = RequestMethod.GET)
	public String customerAccountSummary(Model model, HttpSession session ) {
		String userid = ((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid();
		 List<CustomerAccountInfoVO> customerAccountList=customerService.getAccountDetails(userid);
		 model.addAttribute("customerAccountList",customerAccountList);
		 return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_ACCOUNT_SUMMARY_PAGE;
	}
	
	
	
	@RequestMapping(value = "customer/myAccountSummary/MiniStatement.htm", method = RequestMethod.GET)
	public String customerMiniStatement(Model model, HttpSession session ) {
		String userid = ((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid();
		 List<MiniStatementVO> miniStatementVO=customerService.getTransactionDetails(userid);
		 //model.addAttribute("miniStatementVO",miniStatementVO);
		 model.addAttribute("customerAccountList", miniStatementVO);
		return "miniStatement";
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
			PayeeInfo payee = new PayeeInfo();
			payee.setCustomerId(((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid());
			payee.setPayeeAccountNo(addpayee.getPayeeAccountNo());
			payee.setPayeeName(addpayee.getPayeeName());
			payee.setPayeeNickName(addpayee.getPayeeNickName());
			int random = (int) (Math.random()*1000);
			mailServiceImpl.sendMail("DesiBank", addpayee.getEmail(),"Add payee submission number", "Confirmation code:"+random);
			model.addAttribute("addPayeeInfo", payee);
			model.addAttribute("code",random);
			session.setAttribute("payee", payee);
			//return "confirmPayee";
			 return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_CONFIRM_PAYEE_PAGE;
			//return "addPayee";
	}
	
	

	
	@RequestMapping(value = "customer/transferMoney.htm", method = RequestMethod.GET)
	public String selectPayee( Model model) {
		TransferMoneyForm transferMoneyForm=new TransferMoneyForm();
		model.addAttribute("transferMoneyForm",transferMoneyForm);
		 return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_FUND_TRANSFER_PAGE;
	}
	
	/*@ModelAttribute("fromAccountList")
	public Map<String,String> fromAccountList(HttpSession session){
		Map<String,String> options=new LinkedHashMap<String,String>();
		UserSessionVO userSessionVO= (UserSessionVO) session.getAttribute("userSessionVO");
		String loginid=userSessionVO.getLoginid();
		List<CustomerAccountInfoVO> fromAccountDetailList = customerService.getAccountDetails(loginid);
		 options.put("NONE", "--- Select Account ---");
		 for(CustomerAccountInfoVO item : fromAccountDetailList){
			 String dname=item.getAccountNumber()+"-"+item.getAccountType();
			 options.put(item.getAccountNumber(), dname);
		 }	 
		return options;
	}
	
	
	@ModelAttribute("payeeList")
	public Map<String,String> payeeListForCustomer(HttpSession session){
		Map<String,String> options=new LinkedHashMap<String,String>();
		UserSessionVO userSessionVO= (UserSessionVO) session.getAttribute("userSessionVO");
		String loginid=userSessionVO.getLoginid();
		List<PayeeModel> payeeList = customerService.getPayeeList(loginid);
		 options.put("NONE", "--- Select Payee ---");
		 for(PayeeModel item : payeeList){
			 String dname=item.getPayeeAccountNo()+"-"+item.getPayeeName();
			 options.put(item.getPayeeAccountNo(), dname);
		 }	 
		return options;
	}*/
	
	
	 @RequestMapping(value = "customer/reviewFundTransfer.htm", method = RequestMethod.POST)
	public String transactionDetails(@ModelAttribute("transferMoneyForm") TransferMoneyForm transferMoneyForm  ) {
		 System.out.println("transferMoneyForm = "+transferMoneyForm);
		 return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.REVIEW_FUND_TRANSFER_PAGE;
	}	
	
	 @RequestMapping(value = "customer/transferMoney.htm", method = RequestMethod.POST)
	public String transactionDetails(HttpSession session, HttpServletRequest request ) {
		
		CustomerForm customerForm = customerService.getCustomer(((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid());
		request.setAttribute("customerForm", customerForm);
		UserSessionVO userSessionVO= (UserSessionVO) session.getAttribute("userSessionVO");
		String loginid=userSessionVO.getLoginid();
		List<PayeeModel> payeeList = customerPayeeService.getPayeeList(loginid);
		request.setAttribute("payeeList", payeeList);
		request.setAttribute("selectedPayeeName", request.getParameter("selectedPayeeName"));
		request.setAttribute("amount", request.getParameter("amount"));
		return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_CONFIRM_TRANSFER_PAGE;
	}
	
	
	
	@RequestMapping(value="customer/transactionMoney.htm", method = RequestMethod.POST)
	public String transferMoney(@ModelAttribute("transferMoneyForm") TransferMoneyForm transferMoneyForm,HttpSession session,Model model ) {
			if(transferMoneyForm.getPaymentOption().equals("PayNow")){
				transferMoneyForm.setTransactionMode("transferred");
				transferMoneyForm.setLoginid(((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid());
				customerService.persistCustomerTransaction(transferMoneyForm);
			}
			else{
				//System.out.println(date);
			/*	transaction.setTransactionMode("scheduled");
				transaction.setLoginId(((UserSessionVO)session.getAttribute("userSessionVO")).getLoginid());
				transaction.setDate(date);
				transaction.setId(0);
				customerService.scheduledCustomerTransaction(transaction);*/
			}
			
			
		/*	UserSessionVO userSessionVO= (UserSessionVO) session.getAttribute("userSessionVO");
			String loginid=userSessionVO.getLoginid();
			CustomerForm customerdetail= (CustomerForm) customerService.getUserDetail(loginid);
			model.addAttribute("detail",customerdetail);*/
			 return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_TRANSACTION_SUCCESS;
		
	}
	
	
	@RequestMapping(value="loan/loan.htm", method = RequestMethod.GET)
	public String loanPage( ) {
		
		return "loan";
	}

	
}
