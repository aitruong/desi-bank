package com.desi.bank.data.listener;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.desi.bank.admin.service.AdminService;
import com.desi.bank.constant.DesiBankConstant;
import com.desi.bank.customer.service.impl.CustomerServiceImpl;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.spring.model.UserSessionVO;


/**
 * 
 * @author nagendra
 *  deddddddddddddddddddddd
 *
 */
public class DesiBankDataListener  implements ServletContextListener{
	
	/**
	 * Initiate Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(DesiBankDataListener.class);

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try{
			if(logger.isInfoEnabled()) {
				logger.info("Inside the method contextInitialized");
			}
			if(logger.isInfoEnabled()) {
				logger.info("____Application is loading...........................................................................................");
				logger.info("____Application is loading...........................................................................................");
				logger.info("____Application is loading...........................................................................................");
				logger.info("____Application is loading...........................................................................................");
				logger.info("____Application is loading...........................................................................................");
				logger.info("____Application is loading...........................................................................................");
				logger.info("____Application is loading...........................................................................................");
				logger.info("____Application is loading...........................................................................................");
				logger.info("____Application is loading...........................................................................................");
				logger.info("____Application is loading...........................................................................................");
				logger.info("____Application is loading...........................................................................................");
				logger.info("____Application is loading...........................................................................................");
				logger.info("____Application is loading...........................................................................................");
				logger.info("____Application is loading...........................................................................................");
			}
			//This is new code to access Spring Root Web Application Context
			ServletContext servletContext=arg0.getServletContext();
			ApplicationContext applicationContext=WebApplicationContextUtils.getWebApplicationContext(servletContext);
			CustomerServiceImpl customerServiceImpl=(CustomerServiceImpl)applicationContext.getBean("CustomerServiceImpl");
			System.out.println("______trying to know the proxy type..............."+customerServiceImpl.getClass().getName());
			UserSessionVO userSessionVO=customerServiceImpl.validateCustomerByUserId("admin");
			if(userSessionVO.getLoginid()==null) {
				
				//
				Properties properties=new Properties();
				InputStream inputStream=servletContext.getResourceAsStream(DesiBankConstant.USERS_PROPERTIES_FILE);
				properties.load(inputStream);
				if(logger.isInfoEnabled()) {
					logger.info("Loaded properties  = "+properties);
				}
				CustomerForm customerForm=new CustomerForm();
				customerForm.setQuestion1(properties.getProperty("question1"));
				customerForm.setAnswer1(properties.getProperty("answer1"));
				customerForm.setQuestion2(properties.getProperty("question2"));
				customerForm.setAnswer2(properties.getProperty("answer2"));
				customerForm.setAccountNum(properties.getProperty("accountnum"));
				customerForm.setAddress(properties.getProperty("address"));
				customerForm.setAge(Integer.parseInt(properties.getProperty("age")));
				customerForm.setApproved(properties.getProperty("approved"));
				customerForm.setDob(properties.getProperty("dob"));
				customerForm.setEmail(properties.getProperty("email"));
				customerForm.setGender(properties.getProperty("gender"));
				customerForm.setJobTitle(properties.getProperty("jobtitle"));
				customerForm.setName(properties.getProperty("name"));
			     BCryptPasswordEncoder bcryptEncoder=(BCryptPasswordEncoder)applicationContext.getBean("bcryptEncoder");
				  //Password is encrypted before persisting in database
				customerForm.setPassword(bcryptEncoder.encode(properties.getProperty("password")));
				customerForm.setUserid(servletContext.getInitParameter("userid"));
				customerForm.setRole("admin");
				customerServiceImpl.persistCustomer(customerForm);
				
				CustomerForm ecustomerForm=new CustomerForm();
				ecustomerForm.setQuestion1(properties.getProperty("question1"));
				ecustomerForm.setAnswer1(properties.getProperty("answer1"));
				ecustomerForm.setQuestion2(properties.getProperty("question2"));
				ecustomerForm.setAnswer2(properties.getProperty("answer2"));
				ecustomerForm.setAccountNum("21233445329");
				ecustomerForm.setAddress(properties.getProperty("address"));
				ecustomerForm.setAge(Integer.parseInt(properties.getProperty("age")));
				ecustomerForm.setApproved(properties.getProperty("approved"));
				ecustomerForm.setDob(properties.getProperty("dob"));
				ecustomerForm.setEmail("nagen@synergisticit.com");
				ecustomerForm.setGender(properties.getProperty("gender"));
				ecustomerForm.setJobTitle(properties.getProperty("jobtitle"));
				ecustomerForm.setName(properties.getProperty("name"));
				  //Password is encrypted before persisting in database
				ecustomerForm.setPassword(bcryptEncoder.encode("test"));
				ecustomerForm.setUserid("nagen@synergisticit.com");
				ecustomerForm.setRole("employee");
				customerServiceImpl.persistCustomer(ecustomerForm);
			}
			AdminService adminService=(AdminService)applicationContext.getBean("adminServiceImpl");
			addDefaultAccountNumber(adminService);
			addDefaultTransactionID(adminService);
			
		}catch (Exception e) {
			//e.printStackTrace();
			if(logger.isErrorEnabled()){
				logger.error(e);
			}
			if(logger.isWarnEnabled()){
				logger.warn("Admin already exists or you have some issue with DB connection!");
			}
		}
	}
	
	private void addDefaultAccountNumber(AdminService adminService){
		adminService.addDefaultAccountNumber(1);
	}
	
	public boolean addDefaultTransactionID(AdminService adminService)
	{
		return adminService.addDefaultTransactionID(1);
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
