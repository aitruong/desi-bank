package com.desi.bank.customer.dao;

import java.util.List;

import com.desi.bank.common.dao.entity.CustomerAccountInfo;
import com.desi.bank.common.dao.entity.CustomerQuestionAnswer;
import com.desi.bank.common.dao.entity.CustomerSavingEntity;
import com.desi.bank.common.dao.entity.CustomerTransactionHistory;
import com.desi.bank.common.dao.entity.Login;
import com.desi.bank.common.dao.entity.PayeeInfo;
import com.desi.bank.common.dao.entity.SecurityQuestions;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.customer.web.controller.form.MiniStatementVO;
import com.desi.bank.employee.dao.entity.RegistrationLinksEntity;
import com.spring.model.UserSessionVO;

public interface CustomerDao {
	public String persistCustomer(CustomerForm customer);
	public List<CustomerForm> findCustomers();
	public Login validateCustomer(String userid, String password);
	public UserSessionVO validateCustomerByUserId(String userid);
	public boolean deleteCustomer(int id);
	public CustomerForm getCustomer(int id);
	public CustomerForm getCustomer(String userid);
	public String updateCustomer(CustomerForm customer);
	public List<CustomerAccountInfo> getAccountDetails(String userid);
	public List<CustomerAccountInfo> getAccount(String accountNum);
	public List<MiniStatementVO> getTransactionDetails(String userid);
	public String persistCustomerTransaction(CustomerTransactionHistory transaction);
	public List<SecurityQuestions> securityQns();
	public List<SecurityQuestions> securityQns2();
	public CustomerForm getCustomerDetail(String userid);
	public byte[] findPhoto(int id);
	public List<CustomerQuestionAnswer> getSecurityQns(String id);
	public String updatePass(String userid, String password, String qn1,
			String qn2, String ans1, String ans2);
	public String findEmailByUserid(String userid);
	public List<CustomerTransactionHistory> scheduledTransfer();
	public String scheduledCustomerTransaction(
			CustomerTransactionHistory transaction);
	public String updateScheduledTransaction(
			CustomerTransactionHistory transactionhistory);
	public String savingAccountRequest(CustomerSavingEntity customerSavingEntity);
	public RegistrationLinksEntity findLinkDetailByCuid(String cuid);
	public CustomerSavingEntity findCustomerSavingEnquiryByEmail(String email);
	public byte[] findImageByUserid(String userid);
	public String updatePassword(String userid, String newpassword);
}

