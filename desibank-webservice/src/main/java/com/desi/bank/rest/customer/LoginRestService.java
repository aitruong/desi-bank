package com.desi.bank.rest.customer;

import javax.annotation.PostConstruct;
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

import com.desi.bank.customer.service.CustomerService;
import com.desi.bank.data.util.Encrypter;
import com.desi.bank.rest.admin.ApplicationRestResponse;
import com.desi.bank.rest.vo.LoginVO;
import com.spring.model.LoginJSONResponse;
import com.spring.model.UserSessionVO;

@Component
@Path("/")
public class LoginRestService {
	
	@Autowired
	@Qualifier("CustomerServiceImpl")
	public CustomerService customerService;

	public LoginRestService() {
		System.out.println("In Login service ----------");
		System.out.println("In Login service ----------");
		System.out.println("In Login service ----------");
		System.out.println("In Login service ----------");
	}
	
	
	@PostConstruct
	public void init(){
		System.out.println("__@)@)(@)customerService "+customerService);
	}



	/**
	 * method which is exposed as a restful web service which will validate
	 * userid and password and called by ajax
	 * 
	 * @param userid
	 * @param password
	 * @return
	 */
	// @RequestMapping(value = "ajaxCall", method = RequestMethod.GET)
	@Path("/ajaxCall")
	@GET
	// @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LoginJSONResponse login(@QueryParam(value = "userid") String userid,
			@QueryParam(value = "password") String password) {
		// TaskExecutor te = new TaskExecutor()
		// SimpleAsyncTaskExecutor ste = new SimpleAsyncTaskExecutor();
		Encrypter encrypter = new Encrypter();
		// checking null condition for userid and password
		if (password == null) {
			password = "";
		}
		if (userid == null) {
			userid = "";
		}
		// Password is encrypted before persisting in database
		UserSessionVO userSessionVO = customerService.validateCustomer(userid, encrypter.encrypt(password));
		LoginJSONResponse jsonResponse = new LoginJSONResponse();

		if (userSessionVO.getLocked() != null && userSessionVO.getLocked().equalsIgnoreCase("no")
				&& userSessionVO.getRole() != null) {

			jsonResponse.setStatus("valid");
			jsonResponse.setDescription("Authentication is done!");
			if (userSessionVO.getApproved() != null) {
				jsonResponse.setStatus("invalid");
				jsonResponse.setDescription("Your Account is not approved ,Please contact with admin!!");
			}

		} else {
			if (userSessionVO.getLoginid() == null) {
				jsonResponse.setStatus("invalid");
				jsonResponse.setDescription("Sorry , User name and password is not correct!");
			} else {
				jsonResponse.setStatus("invalid");
				jsonResponse.setDescription("Sorry , Your account is locked ,contactto bank admin!");
			}

		}
		return jsonResponse;
	}

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ApplicationRestResponse authUser(LoginVO loginVO) {
		ApplicationRestResponse applicationRestResponse = new ApplicationRestResponse();
		Encrypter encrypter = new Encrypter();
		// checking null condition for userid and password
		// Password is encrypted before persisting in database
		String password = loginVO.getPassword() == null ? "" : loginVO.getPassword();
		UserSessionVO userSessionVO = customerService.validateCustomer(loginVO.getUsername(),
				encrypter.encrypt(password));
		if (userSessionVO.getLocked() != null && userSessionVO.getLocked().equalsIgnoreCase("no")
				&& userSessionVO.getRole() != null) {
			applicationRestResponse.setMessage("you are valid user");
			applicationRestResponse.setRole("customer");
			applicationRestResponse.setStatus("success");
		} else {
			applicationRestResponse.setMessage("you are valid user");
			applicationRestResponse.setRole("unknown");
			applicationRestResponse.setStatus("fail");
		}
		return applicationRestResponse;
	}
}
