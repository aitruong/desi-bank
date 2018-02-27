package com.desi.bank.employee.web.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.desi.bank.constant.DesiBankConstant;
import com.desi.bank.constant.DesiBankNavigationConstant;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.customer.web.controller.form.CustomerSavingForm;
import com.desi.bank.data.listener.DesiBankDataListener;
import com.desi.bank.data.util.DesiBankUtils;
import com.desi.bank.email.service.EmailMessageSenderService;
import com.desi.bank.email.service.EmailVO;
import com.desi.bank.email.service.ICustomerEmailService;
import com.desi.bank.email.service.TestVO;
import com.desi.bank.employee.service.EmployeeService;
import com.desi.bank.employee.web.controller.form.ApplicationMessageResponse;
import com.desi.bank.employee.web.controller.form.RegistrationLinksForm;
import com.desi.bank.employee.web.controller.form.RejectSavingRequestForm;
import com.desi.bank.util.UniqueLinkGenaratorUtil;

/**
 * 
 * @author nagendra
 *
 */
@Controller
@RequestMapping(value="/employee")
public class BankEmployeeController {
	
	/**
	 * Initiate Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(BankEmployeeController.class);
	
	
	@Value("${registration.link.validity.hrs}")
	  private String  linkvalidity;
	
	@Autowired
	@Qualifier ("EmailMessageSenderService")
	private EmailMessageSenderService emailMessageSenderService;
	
	
	@Autowired
	@Qualifier ("EmployeeServiceImpl")
	private EmployeeService employeeService;
	
	
	@Autowired
	@Qualifier("CustomerEmailService")
	private ICustomerEmailService customerEmailService;
	
	//Spring Bean Life cycle 
	//Aware interface
	@PostConstruct
	public void checkValue(){
		if(logger.isDebugEnabled()) {
			   logger.debug("linkvalidity = "+linkvalidity);
		}
	}
	
	@RequestMapping(value="lockUnlockCustomer",method=RequestMethod.GET)
	@ResponseBody public String lockUnlockCustomer(@RequestParam("lockStatus") String lockStatus,@RequestParam("loginid") String loginid,Model  model){
		String resultStatus=employeeService.lockUnlockCustomer(loginid,lockStatus);
		return resultStatus;
	}
	
	
	@RequestMapping(value="rejectSavingRequest",method=RequestMethod.POST)
@ResponseBody	public String rejectSavingSavingAccountRequest(@ModelAttribute RejectSavingRequestForm rejectSavingRequestForm,HttpServletRequest request,Model  model){
		String result=employeeService.rejectSavingAccountRequests(rejectSavingRequestForm);
		ApplicationMessageResponse applicationMessageResponse=new ApplicationMessageResponse();
		applicationMessageResponse.setStatus(DesiBankConstant.SUCCESS);
		 //write the code
		if(logger.isInfoEnabled()) {
			logger.info("Please wait.....email is sending........................................");
		}
	/*	SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nagen@synergisticit.com");
        message.setTo(rejectSavingRequestForm.getEmail());
        message.setSubject("Regarding rejection of your openining saving account request!");
        message.setText("Reason of rejection :  = "+rejectSavingRequestForm.getReason());
        mailSender.send(message);*/
		TestVO testVO=new TestVO();
		testVO.setFrom("nagen@gmail.com");
		testVO.setName("DesiBank");
		testVO.setSubject("Test Email");
		testVO.setMessage("Testing testing testing");
		testVO.setTo("nagendra@gmail.com");
		emailMessageSenderService.sendSampleData(testVO);
		System.out.println("___message has beeb send to the message broker!~~~~~~~~~~~~~~~~~~~~~~");
		EmailVO mail=new EmailVO();
		mail.setBaseUrl(DesiBankUtils.getServerBaseURL(request));
		mail.setFrom("desibankproject@gmail.com");
		mail.setLink("www.gmail.com/registration/link?cid=ertret345ert533634ge64");
		mail.setName(rejectSavingRequestForm.getCustomerName());
		mail.setSubject("Regarding rejection of your openining saving account request!");
		mail.setTo(rejectSavingRequestForm.getEmail());
		if(logger.isDebugEnabled()) {
			   logger.debug("mail = "+mail);
		}
		customerEmailService.sendRegistrationEmail(mail);
		 return "success";
	}
	
	@RequestMapping(value="pendingSavingRequest",method=RequestMethod.GET)
	public String showPendingSavingAccountRequest(Model  model){
		List<CustomerSavingForm> customerSavingFormList=employeeService.findPendingSavingAccountRequests();
		model.addAttribute("customerSavingFormList", customerSavingFormList);
		 return DesiBankNavigationConstant.EMPLOYEE_BASE+DesiBankNavigationConstant.PENDING_SAVING_REQUESTS_PAGE;
	}
	
	@RequestMapping(value="pendingSavingAccountRequests",method=RequestMethod.GET)
	public String showPendingSavingAccountRequests(Model  model){
		List<CustomerForm> customerFormList=employeeService.findPendingSavingAccountApprovalRequests();
		model.addAttribute("customerFormList", customerFormList);
		 return DesiBankNavigationConstant.EMPLOYEE_BASE+DesiBankNavigationConstant.PENDING_SAVING_ACCOUNT_REQUESTS_PAGE;
	}
	
	/**
	 *  This is showing page for already approved customer accounts
	 * @param model
	 * @return
	 */
	@RequestMapping(value="approvedCustomersAccount",method=RequestMethod.GET)
	public String approvedCustomersAccount(Model  model){
		List<CustomerForm> customerFormList=employeeService.findSavingApprovedAccount();
		model.addAttribute("customerFormList", customerFormList);
		 return DesiBankNavigationConstant.EMPLOYEE_BASE+DesiBankNavigationConstant.APPROVED_CUSTOMERS_ACCOUNT_PAGE;
	}
	
	@ModelAttribute("filterOptions")
	public List<String> filterOptions(){
		List<String> list=new ArrayList<String>();
		list.add("Select");
		list.add("Today");
		list.add("Yesterday");
		list.add("Last 3 days");
		list.add("Last 5 days");
		list.add("Last Week");
		list.add("All");
		return list;
	}
	
	@RequestMapping(value="approveSavingRequest",method=RequestMethod.POST)
		@ResponseBody	public String approveSavingRequest(@ModelAttribute RejectSavingRequestForm approveSavingRequestForm,HttpServletRequest request,Model  model){
		System.out.println(approveSavingRequestForm);
		employeeService.savingApproveAccountRequests(approveSavingRequestForm);
		//We have to generate unique link
		
		//http://localhost/desi-bank
		String baseURL=DesiBankUtils.getServerBaseURL(request)+"/"+DesiBankConstant.REGISTRATION_URL;
		String cuid=UniqueLinkGenaratorUtil.findUniqueCuid();
		String registrationLink=baseURL+cuid;
		RegistrationLinksForm  linksForm=new RegistrationLinksForm();
		linksForm.setComment("Link is generated");
		Timestamp currentTime=new Timestamp(new Date().getTime());
		linksForm.setDoe(currentTime);
		linksForm.setEmail(approveSavingRequestForm.getEmail());
		linksForm.setExphrs(Integer.parseInt(linkvalidity));  //This value should come from properties file................
		Calendar c = Calendar.getInstance();
        c.setTime(currentTime);
        c.add(Calendar.HOUR, Integer.parseInt(linkvalidity));
        Date ExpirationDate = c.getTime();
        Timestamp linkexpdate=new Timestamp(ExpirationDate.getTime());
        linksForm.setLinkexpiredate(linkexpdate);
		linksForm.setLinkurl(cuid);
		if(logger.isDebugEnabled()) {
			   logger.debug("linksForm = "+linksForm);
		}
		employeeService.saveRegistrationLink(linksForm);
		
		try {
					EmailVO mail=new EmailVO();
					mail.setBaseUrl(DesiBankUtils.getServerBaseURL(request));
					mail.setFrom("desibankproject@gmail.com");
					mail.setLink(registrationLink);
					mail.setName(approveSavingRequestForm.getCustomerName());
					mail.setSubject("Regarding Registration link to open a saving account");
					mail.setTo(approveSavingRequestForm.getEmail());
					customerEmailService.sendRegistrationEmail(mail);
		}catch(Exception ex) {
			StringWriter stringWriter=new StringWriter();
			ex.printStackTrace(new PrintWriter(stringWriter));
			if(logger.isErrorEnabled()){
				logger.error(stringWriter.toString());
			}
		}
		ApplicationMessageResponse applicationMessageResponse=new ApplicationMessageResponse();
		applicationMessageResponse.setStatus("success");
		applicationMessageResponse.setMessage("Hey this request has been approved seuccessfully.....................");
		//We can convert 
		return "success";
	}	

}
