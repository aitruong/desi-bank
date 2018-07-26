package com.desi.bank.customer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CachePut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.desi.bank.common.advice.MessageLogger;
import com.desi.bank.common.dao.entity.CustomerAccountInfo;
import com.desi.bank.common.dao.entity.CustomerQuestionAnswer;
import com.desi.bank.common.dao.entity.CustomerSavingEntity;
import com.desi.bank.common.dao.entity.CustomerTransactionHistory;
import com.desi.bank.common.dao.entity.Login;
import com.desi.bank.common.dao.entity.PayeeInfo;
import com.desi.bank.common.dao.entity.SecurityQuestions;
import com.desi.bank.customer.dao.CustomerDao;
import com.desi.bank.customer.service.CustomerService;
import com.desi.bank.customer.web.controller.TransferMoneyForm;
import com.desi.bank.customer.web.controller.form.CustomerAccountInfoVO;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.customer.web.controller.form.CustomerSavingForm;
import com.desi.bank.customer.web.controller.form.MiniStatementVO;
import com.desi.bank.customer.web.controller.form.PayeeModel;
import com.desi.bank.employee.dao.entity.RegistrationLinksEntity;
import com.desi.bank.employee.web.controller.form.RegistrationLinksForm;
import com.spring.model.UserSessionVO;

@Service("CustomerServiceImpl")
@Scope("singleton")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	@Qualifier("CustomerDaoImpl")
	private CustomerDao customerDao;
	
	@Override
	public String savingAccountRequest(CustomerSavingForm customerSavingForm) {
			//
		CustomerSavingEntity customerSavingEntity=new CustomerSavingEntity();
		BeanUtils.copyProperties(customerSavingForm, customerSavingEntity);
		String result=customerDao.savingAccountRequest(customerSavingEntity);
		return result;
	}
	
	@Override
	public String updatePassword(String userid,String newpassword) {
		return customerDao.updatePassword(userid, newpassword);
	}
	
	@Override
	public UserSessionVO validateCustomerByUserId(String userid) {
		return customerDao.validateCustomerByUserId(userid);
	}
	
	@Override
	public String persistCustomer(CustomerForm customer) {
		
		return customerDao.persistCustomer(customer);
	}

	@Override
	public List<CustomerForm> findCustomers() {
		return customerDao.findCustomers();
	}

	public byte[] findImageByUserid(String userid){
		return customerDao.findImageByUserid(userid);
	}
	
	@MessageLogger(message="executing method validateCustomer")
	@Override
	public UserSessionVO validateCustomer(String userid, String password) {
				Login login=customerDao.validateCustomer(userid,password);
			// UserSessionVO userSessionVO = new UserSessionVO();
				UserSessionVO userSessionVO = BeanUtils
						.instantiate(UserSessionVO.class);
				if (login!=null) {
					BeanUtils.copyProperties(login, userSessionVO);
				}
				return userSessionVO;
	}

	@Override
	public boolean deleteCustomer(int id) {
		return customerDao.deleteCustomer(id);
	}

	@Override
	public CustomerForm getCustomer(int id) {
		return customerDao.getCustomer(id);
	}

	@Override
	public CustomerForm getCustomer(String userid) {
		return customerDao.getCustomer(userid);
	}

	@Override
	public String updateCustomer(CustomerForm customer) {
		return customerDao.updateCustomer(customer);
	}

	@Override
	public List<CustomerAccountInfoVO> getAccountDetails(String userid) {
		List<CustomerAccountInfo> customerAccountInfos=customerDao.getAccountDetails(userid);
		List<CustomerAccountInfoVO> accountInfoVOs=new ArrayList<CustomerAccountInfoVO>();
		for(CustomerAccountInfo accountInfo:customerAccountInfos){
			CustomerAccountInfoVO accountInfoVO=new CustomerAccountInfoVO();
			BeanUtils.copyProperties(accountInfo, accountInfoVO);
			accountInfoVOs.add(accountInfoVO);
		}
		return accountInfoVOs;
	}

	@Override
	public List<CustomerAccountInfo> getAccount(String accountNum) {
		return customerDao.getAccount(accountNum);
	}

	@Override
	public List<MiniStatementVO> getTransactionDetails(String userid) {
		return customerDao.getTransactionDetails(userid);
	}
	
	
	

	
	
	

	@Override
	public String persistCustomerTransaction(
			TransferMoneyForm transaction) {
		CustomerTransactionHistory customerTransactionHistory=new CustomerTransactionHistory();
		customerTransactionHistory.setAmount(transaction.getAmount());
		customerTransactionHistory.setDescription(transaction.getDescription());
		customerTransactionHistory.setDate(new Date());
		customerTransactionHistory.setFromAccountNumber(transaction.getFromAccount());
		customerTransactionHistory.setLoginId(transaction.getLoginid());
		customerTransactionHistory.setToAccountNumber(transaction.getSelectedPayeeName());
		customerTransactionHistory.setTransactionMode(transaction.getTransactionMode());
		return customerDao.persistCustomerTransaction(customerTransactionHistory);
	}

   // @Cacheable(value="desibank-cache")
	@Override
	public List<SecurityQuestions> securityQns() {
    	System.out.println(".............SecurityQuestions is cached..........................at ."+new Date());
		List<SecurityQuestions> sqList = (List<SecurityQuestions>)customerDao.securityQns();
		return sqList;
	}
	
	public List<SecurityQuestions> securityQns2() {
		List<SecurityQuestions> sqList = (List<SecurityQuestions>)customerDao.securityQns2();
		return sqList;
	}
	
	
	@CachePut(value="desibank-cache",key="#userid")
	@Override
	public CustomerForm getUserDetail(String userid){
		CustomerForm detail = (CustomerForm)customerDao.getCustomerDetail(userid);
		return detail;
	}

	@Override
	public byte[] findPhotoById(int id) {
		// TODO Auto-generated method stub
		byte[] photo = customerDao.findPhoto(id);
		return photo;
	}

	@Override
	public List<CustomerQuestionAnswer> getSecurityQn(String id) {
		// TODO Auto-generated method stub
		List<CustomerQuestionAnswer> qns = (List<CustomerQuestionAnswer>)customerDao.getSecurityQns(id);
		return qns;
	}

	@Override
	public String checkPassword(String userid, String password, String qn1,
			String qn2, String ans1, String ans2) {
			
			String  updatePass =  (String) customerDao.updatePass(userid, password, qn1,
					 qn2, ans1, ans2);
		
		return updatePass;
	}

	
	//concurrent
	///userid  - return value
	@CachePut(value="desibank-cache")
	@Override
	public String findEmailByUserid(String userid) {
		System.out.println("_#_##))#(#(#(#(#(#(#(#(##*");
		String email= customerDao.findEmailByUserid(userid);
		return email;
	}
	
	@Override
	public RegistrationLinksForm findLinkDetailByCuid(String cuid) {
		RegistrationLinksEntity linksEntity=customerDao.findLinkDetailByCuid(cuid);
		RegistrationLinksForm registrationLinksForm=new RegistrationLinksForm();
		BeanUtils.copyProperties(linksEntity, registrationLinksForm);
		return registrationLinksForm;
	}
	
	@Override
	public CustomerSavingForm findCustomerSavingEnquiryByEmail(String email) {
		CustomerSavingEntity linksEntity=customerDao.findCustomerSavingEnquiryByEmail(email);
		CustomerSavingForm customerSavingForm=new CustomerSavingForm();
		BeanUtils.copyProperties(linksEntity, customerSavingForm);
		return customerSavingForm;
	}
	

	@Override
	public List<CustomerTransactionHistory> scheduledTransfer() {
		return customerDao.scheduledTransfer();
		
	}

	@Override
	public String scheduledCustomerTransaction(
			CustomerTransactionHistory transaction) {
		return customerDao.scheduledCustomerTransaction(transaction);
	}

	@Override
	public void updateScheduledTransaction(
			CustomerTransactionHistory transactionhistory) {
		customerDao.updateScheduledTransaction(transactionhistory);
	}
	
	
}
