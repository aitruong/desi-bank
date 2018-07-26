package com.desi.bank.customer.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desi.bank.app.message.DesiBankAppMessages;
import com.desi.bank.common.dao.entity.CustomerQuestionAnswer;
import com.desi.bank.constant.DesiBankApplicationRole;
import com.desi.bank.constant.DesiBankConstant;
import com.desi.bank.constant.DesiBankNavigationConstant;
import com.desi.bank.customer.service.CustomerService;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.data.util.Encrypter;
import com.desi.bank.employee.service.EmployeeService;
import com.spring.model.LoginJSONResponse;
import com.spring.model.UserSessionVO;

@Controller
public class UserAuthController {
	
	/**
	 * Initiate Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(UserAuthController.class);
	
	
	@Autowired
	@Qualifier("CustomerServiceImpl")
	public CustomerService customerService;
	
	@Autowired
	@Qualifier("EmployeeServiceImpl")
	public EmployeeService employeeService;
	
	
	/* @Value("${jdbc.username}")
	  private String username;
	 
	 @PostConstruct
	 public void userAuthController() {
		 	System.out.println("UserAuthController ..............................username = "+username);
			System.out.println("UserAuthController ..............................username = "+username);
			System.out.println("UserAuthController ..............................username = "+username);
			System.out.println("UserAuthController ..............................username = "+username);
	 }*/
	
	
	
	@RequestMapping(value = "login.htm", method = RequestMethod.GET)
	public String loginPage() {
			return "login";
	}
	
	@RequestMapping(value = "customerHome.htm", method = RequestMethod.GET)
	public String customerHome(HttpSession session) {
		UserSessionVO sessionVO=(UserSessionVO)session.getAttribute(DesiBankConstant.USER_SESSION_DATA);
		if(sessionVO.getLlt()==null){
			 //write   the code to force to customer to change the password
			return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CHANGE_PASSWORD_PAGE;
		}else{
		  return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_HOME_PAGE;
		}  
	}
	
	@RequestMapping(value = "employeeHome.htm", method = RequestMethod.GET)
	public String employeeHome() {
			  return DesiBankNavigationConstant.EMPLOYEE_BASE+DesiBankNavigationConstant.EMPLOYEE_HOME_PAGE;
	}

	/**
	 * method which is exposed as a restful web service
	 * which will validate userid and password and called by ajax   
	 * @param userid
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "ajaxCall.htm", method = RequestMethod.GET)
	public @ResponseBody LoginJSONResponse login(@RequestParam(value = "userid", required = false) String userid,
			@RequestParam(value = "password", required = false) String password) {
		//TaskExecutor te = new TaskExecutor() 
		//SimpleAsyncTaskExecutor ste = new SimpleAsyncTaskExecutor();
		Encrypter encrypter=new Encrypter();
		//checking null condition for userid and password
		if(password==null){
			password=""; 
		}
		if(userid==null){
			userid="";
		}
		//Password is encrypted before persisting in database
		UserSessionVO userSessionVO = customerService.validateCustomer(userid, encrypter.encrypt(password));
		LoginJSONResponse jsonResponse=new LoginJSONResponse();
		
		 if (userSessionVO.getLocked()!=null && userSessionVO.getLocked().equalsIgnoreCase("no") && userSessionVO.getRole() != null ){
			 
			jsonResponse.setStatus("valid");
			jsonResponse.setDescription("Authentication is done!");
			if(userSessionVO.getApproved()!=null){
				jsonResponse.setStatus("invalid");
				jsonResponse.setDescription("Your Account is not approved ,Please contact with admin!!");
			}
			
		}else{
			if(userSessionVO.getLoginid()==null){
				jsonResponse.setStatus("invalid");
				jsonResponse.setDescription("Sorry , User name and password is not correct!");	
			}else{
				jsonResponse.setStatus("invalid");
				jsonResponse.setDescription("Sorry , Your account is locked ,contactto bank admin!");
			}
			
		}
		return jsonResponse;
	}	
	
	@RequestMapping(value="homePage",method = RequestMethod.GET)
    public String handleRequestInternal(Model model,HttpSession session) throws Exception {
				       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				       //retrieving the role of the logged in user.
				       Collection<? extends GrantedAuthority> grantedList=authentication.getAuthorities();
				       //Here we are assuming last role present inside the list will be actual role of
				       //logged in user.
				       String nextPage="";
				       if(grantedList!=null && grantedList.size()>0){
				         Iterator<? extends GrantedAuthority> iterator=grantedList.iterator();
				         if(iterator.hasNext()){
				             GrantedAuthority ga=iterator.next();
				             nextPage=ga.getAuthority(); //
				         }
				       }
       
				   	UserSessionVO userSessionVO = customerService.validateCustomerByUserId(authentication.getName());
					
					if(userSessionVO.getLoginid()==null) {
						// setting data in request scope using Model interface
						///model.addAttribute("error", DesiBankAppMessages.SORRY_USE_NAME_PASSWORD_NOT_CORRECT);
						return "redirect:/unautherized.jsp";
					}
					
					if(logger.isDebugEnabled()) {
						logger.debug("User is locked ? = "+userSessionVO.getLocked());
						logger.debug("Current user role is  =  "+userSessionVO.getRole());
					}
	
				   if ( userSessionVO.getLocked().equalsIgnoreCase("no") && userSessionVO.getRole() != null ){
					    session.setAttribute(DesiBankConstant.USER_SESSION_DATA, userSessionVO);
					   if (userSessionVO.getRole().equalsIgnoreCase(DesiBankApplicationRole.CUSTOMER.getValue())){
						 //return customer detail from customer_tbl
						   CustomerForm customerdetail= (CustomerForm) customerService.getUserDetail(userSessionVO.getLoginid());
						   model.addAttribute("detail",customerdetail);
							if(userSessionVO.getLlt()==null){
								 //write   the code to force to customer to change the password
								return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CHANGE_PASSWORD_PAGE;
							}else{
								return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_HOME_PAGE;
							}
					   }else if(userSessionVO.getRole().equalsIgnoreCase(DesiBankApplicationRole.ADMIN.getValue()))	{
					       //return "admin";
					       return DesiBankNavigationConstant.ADMIN_BASE+DesiBankNavigationConstant.ADMIN_HOME_PAGE;
				       }
					   else if(userSessionVO.getRole().equalsIgnoreCase(DesiBankApplicationRole.EMPLOYEE.getValue()))	{
						   //Code to fetch number of request
						   int pendingRequestCount=employeeService.findPendingSavingAccountRequestsCount();
						   //Using model we can transfer our data from controller to view
						   //it 
						   model.addAttribute("pendingRequestCount", pendingRequestCount);
					       return DesiBankNavigationConstant.EMPLOYEE_BASE+DesiBankNavigationConstant.EMPLOYEE_HOME_PAGE;
				       } else	{
				    	   	model.addAttribute("error", DesiBankAppMessages.SORRY_USE_NAME_PASSWORD_NOT_CORRECT);
				    	   	return "redirect:/index.jsp";
				       }
				}else{
					// setting data in request scope using Model interface
					model.addAttribute("error", DesiBankAppMessages.SORRY_YOU_DO_NOT_HAVE_ROLE_TO_LOGIN);
					return "redirect:/index.jsp";
				 }
       
    }
	
	/**
	 * 
	 * @param model
	 * @param userid
	 * @param password
	 * @param session
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "validate.htm", method = RequestMethod.POST)
	public String goAhead(Model model,
			@RequestParam(value = "userid", required = false) String userid,
			@RequestParam(value = "password", required = false) String password,HttpSession session) {
		 Encrypter encrypter=new Encrypter();
		  //Password is encrypted before persisting in database
		UserSessionVO userSessionVO = customerService.validateCustomer(userid, encrypter.encrypt(password));
		
			if(userSessionVO.getLoginid()==null) {
				// setting data in request scope using Model interface
				model.addAttribute("error", DesiBankAppMessages.SORRY_USE_NAME_PASSWORD_NOT_CORRECT);
				return "login";
			}
			
			if(logger.isDebugEnabled()) {
				logger.debug("User is locked ? = "+userSessionVO.getLocked());
				logger.debug("Current user role is  =  "+userSessionVO.getRole());
			}
			
		   if ( userSessionVO.getLocked().equalsIgnoreCase("no") && userSessionVO.getRole() != null ){
			   session.setAttribute("userSessionVO", userSessionVO);
			   if (userSessionVO.getRole().equalsIgnoreCase(DesiBankApplicationRole.CUSTOMER.getValue())){
				 //return customer detail from customer_tbl
				   CustomerForm customerdetail= (CustomerForm) customerService.getUserDetail(userid);
				   model.addAttribute("detail",customerdetail);
				   return DesiBankNavigationConstant.CUSTOMER_BASE+DesiBankNavigationConstant.CUSTOMER_HOME_PAGE;
			   }else if(userSessionVO.getRole().equalsIgnoreCase(DesiBankApplicationRole.ADMIN.getValue()))	{
	    	       //return "admin";
	    	       return DesiBankNavigationConstant.ADMIN_BASE+DesiBankNavigationConstant.ADMIN_HOME_PAGE;
		       }
			   else if(userSessionVO.getRole().equalsIgnoreCase(DesiBankApplicationRole.EMPLOYEE.getValue()))	{
	    	       return DesiBankNavigationConstant.EMPLOYEE_BASE+DesiBankNavigationConstant.EMPLOYEE_HOME_PAGE;
		       } else	{
		    	   	model.addAttribute("error", DesiBankAppMessages.SORRY_USE_NAME_PASSWORD_NOT_CORRECT);
					return "login";
		       }
		}else{
			// setting data in request scope using Model interface
			model.addAttribute("error", DesiBankAppMessages.SORRY_YOU_DO_NOT_HAVE_ROLE_TO_LOGIN);
			return "login";
		 }
	}
	/*  method to fetch photo for profile page(after login)  */
	@RequestMapping(value = "showPhotoById.htm", method = RequestMethod.GET)
	public void showPhotoById(HttpServletRequest request,HttpServletResponse response) {
		String id=request.getParameter("id");
		byte[] photo=customerService.findPhotoById(Integer.parseInt(id));
		response.setContentType("image/jpeg");
		try {
			ServletOutputStream out = response.getOutputStream();
			if (photo != null)
				out.write(photo); //here we are writing photo into body of response
		} catch (IOException e) {
			StringWriter errorMessage=new StringWriter();
			PrintWriter printWriter=new PrintWriter(errorMessage);
			e.printStackTrace(printWriter);
			if(logger.isErrorEnabled()) {
				logger.error(printWriter.toString());
			}
		}
	}
	
	@RequestMapping(value = "getSecurityQn.htm", method = RequestMethod.GET)
	public String userSecurityQns(Model model, HttpSession session){
		UserSessionVO userSessionVO= (UserSessionVO) session.getAttribute("userSessionVO");
		String loginid=userSessionVO.getLoginid();
		List<CustomerQuestionAnswer> qns= (List<CustomerQuestionAnswer>)customerService.getSecurityQn(loginid);
		//request.setAttribute("id", id);
		model.addAttribute("id",loginid);
		model.addAttribute("qn1",qns.get(0).getQuestion());
		model.addAttribute("qn2",qns.get(1).getQuestion());
		return "custChangePassword";
	}
	
	@RequestMapping(value = "changePass.htm", method = RequestMethod.POST)
	public String changePass(HttpSession session,Model model,
			//@RequestParam(value = "id", required = false) String userid,
			@RequestParam(value = "qn1", required = false) String qn1,
			@RequestParam(value = "qn2", required = false) String qn2,
			HttpServletRequest request){
//		String id=request.getParameter("id");
//		String qn1 =request.getParameter("qn1"); 
//		String qn2 =request.getParameter("qn2");
		UserSessionVO userSessionVO= (UserSessionVO) session.getAttribute("userSessionVO");
		String userid=userSessionVO.getLoginid();
		String pass=request.getParameter("conf_pass");
		String ans1=request.getParameter("ans1");
		String ans2=request.getParameter("ans2");
		Encrypter encrypter=new Encrypter();
		String status = customerService.checkPassword(userid, encrypter.encrypt(pass), qn1,qn2,ans1,ans2);
		
		if(status=="success"){
			return "login";
		}
		model.addAttribute("message","Wrong answer");
		List<CustomerQuestionAnswer> qns= (List<CustomerQuestionAnswer>)customerService.getSecurityQn(userid);
		model.addAttribute("id",userid);
		model.addAttribute("qn1",qns.get(0).getQuestion());
		model.addAttribute("qn2",qns.get(1).getQuestion());
		return "custChangePassword";
	}
	
	@RequestMapping(value = "/auth/slogout.htm", method = RequestMethod.GET)
	public String loginLogout(HttpSession session) {
		return "redirect:/index.jsp?message=you have logged out from the application successfully";
	}
	
	@RequestMapping(value = "/auth/sessionTimeoutPage.htm", method = RequestMethod.GET)
	public String sessionTimeoutPage(HttpSession session) {
		return "redirect:/index.jsp";
	}
	
	@RequestMapping(value = "/auth/invalid-auth.htm", method = RequestMethod.GET)
	public  String invalidLogin(HttpSession session,final RedirectAttributes  redirectAttributes) {
			System.out.println(")(@*&^#^#^#%#%#%#%#%%#%#%#%#%#$$$$$$$$$$$$$$$$$$$$$$$$$");
			redirectAttributes.addFlashAttribute("open","yes");
			redirectAttributes.addFlashAttribute("ploginMessage","username & password are not correct!");
			return "redirect:/index.jsp?loginMessage=username & password are not correct!&open=yes";
	}
	
  }


	

