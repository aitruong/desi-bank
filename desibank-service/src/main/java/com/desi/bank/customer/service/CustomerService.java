package com.desi.bank.customer.service;

import java.util.List;

import com.desi.bank.common.dao.entity.CustomerAccountInfo;
import com.desi.bank.common.dao.entity.CustomerQuestionAnswer;
import com.desi.bank.common.dao.entity.CustomerTransactionHistory;
import com.desi.bank.common.dao.entity.PayeeInfo;
import com.desi.bank.common.dao.entity.SecurityQuestions;
import com.desi.bank.customer.web.controller.TransferMoneyForm;
import com.desi.bank.customer.web.controller.form.CustomerAccountInfoVO;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.customer.web.controller.form.CustomerSavingForm;
import com.desi.bank.customer.web.controller.form.MiniStatementVO;
import com.desi.bank.customer.web.controller.form.PayeeModel;
import com.desi.bank.employee.web.controller.form.RegistrationLinksForm;
import com.spring.model.UserSessionVO;

/**
 * 
 * @author ADMIN-2
 *  This is service interface
 *
 */
public interface CustomerService {
	
	public String persistCustomer(CustomerForm customer);

	public List<CustomerForm> findCustomers();

	public UserSessionVO validateCustomer(String userid, String password);

	public boolean deleteCustomer(int id);

	public CustomerForm getCustomer(int id);

	public CustomerForm getCustomer(String userid);

	public String updateCustomer(CustomerForm customer);

	public List<CustomerAccountInfoVO> getAccountDetails(String userid);

	public List<CustomerAccountInfo> getAccount(String accountNum);

	public List<MiniStatementVO> getTransactionDetails(String userid);


	public String persistCustomerTransaction(
			TransferMoneyForm transaction);
	
	public List<SecurityQuestions> securityQns();
	
	public List<SecurityQuestions> securityQns2();
	
	public CustomerForm getUserDetail(String userid);

	UserSessionVO validateCustomerByUserId(String userid);

	public byte[] findPhotoById(int id);

	public List<CustomerQuestionAnswer> getSecurityQn(String loginid);

	public String checkPassword(String userid, String password, String qn1,
			String qn2, String ans1, String ans2);

	public String findEmailByUserid(String userid);

	public List<CustomerTransactionHistory> scheduledTransfer();

	public String scheduledCustomerTransaction(
			CustomerTransactionHistory transaction);

	public void updateScheduledTransaction(
			CustomerTransactionHistory transactionhistory);

	public 	String savingAccountRequest(CustomerSavingForm customerSavingForm);

	public RegistrationLinksForm findLinkDetailByCuid(String cuid);

	public CustomerSavingForm findCustomerSavingEnquiryByEmail(String email);

	public byte[] findImageByUserid(String userid);


}
