package com.desi.bank.employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.desi.bank.common.dao.entity.Customer;
import com.desi.bank.common.dao.entity.CustomerAccountInfo;
import com.desi.bank.common.dao.entity.CustomerSavingEntity;
import com.desi.bank.customer.web.controller.form.CustomerAccountInfoVO;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.customer.web.controller.form.CustomerSavingForm;
import com.desi.bank.email.service.ICustomerEmailService;
import com.desi.bank.employee.dao.EmployeeDao;
import com.desi.bank.employee.dao.entity.RegistrationLinksEntity;
import com.desi.bank.employee.dao.entity.RejectSavingRequestEntity;
import com.desi.bank.employee.service.EmployeeService;
import com.desi.bank.employee.web.controller.form.CustomerAccountRegistrationVO;
import com.desi.bank.employee.web.controller.form.RegistrationLinksForm;
import com.desi.bank.employee.web.controller.form.RejectSavingRequestForm;

@Service("EmployeeServiceImpl")
@Scope("singleton")
public class EmployeeServiceImpl  implements EmployeeService{

	@Autowired
	@Qualifier("EmployeeDaoImpl")
	private EmployeeDao employeeDao;
	
	
	@Autowired
	@Qualifier("CustomerEmailService")
	private ICustomerEmailService customerEmailService;
	
	@Override
	public String sendAccountSummaryEmail(CustomerAccountRegistrationVO customerAccountRegistrationVO){
		return customerEmailService.sendAccountCreationEmail(customerAccountRegistrationVO);
	}
	
	
	/**
	 *  This is should be in the transaction......................
	 */
	
	@Override
	public CustomerAccountInfoVO  createCustomerAccount(String userid) {
		CustomerAccountInfoVO customerAccountInfoVO=new CustomerAccountInfoVO();
		CustomerAccountInfo customerAccountInfo	=employeeDao.createCustomerAccount(userid);
		BeanUtils.copyProperties(customerAccountInfo, customerAccountInfoVO);
		//customerAccountInfoVO.setAvBalance(customerAccountInfo.getAvBalance());
		//customerAccountInfoVO.setTavBalance(customerAccountInfo.getTavBalance());
		return  customerAccountInfoVO;
	}
	
	@Override
	public String  rejectSavingAccountRequests(RejectSavingRequestForm rejectSavingRequestForm) {
		RejectSavingRequestEntity entity=new RejectSavingRequestEntity();
		BeanUtils.copyProperties(rejectSavingRequestForm, entity);
		String status	=employeeDao.rejectSavingAccountRequests(entity);
		return  status;
	}
	
	@Override
	public String  savingApproveAccountRequests(RejectSavingRequestForm approvalSavingRequestForm) {
		RejectSavingRequestEntity entity=new RejectSavingRequestEntity();
		BeanUtils.copyProperties(approvalSavingRequestForm, entity);
		String status	=employeeDao.savingApproveAccountRequests(entity);
		return  status;
	}
	
	@Override
	public String  saveRegistrationLink(RegistrationLinksForm registrationLinksForm) {
		RegistrationLinksEntity entity=new RegistrationLinksEntity();
		BeanUtils.copyProperties(registrationLinksForm, entity);
		String status	=employeeDao.saveRegistrationLink(entity);
		return  status;
	}
	
	@Override
	public String lockUnlockCustomer(String loginid, String status){
		 return employeeDao.lockUnlockCustomer(loginid, status);
	} 
	
	@Override
	public List<CustomerSavingForm>  findPendingSavingAccountRequests() {
		List<CustomerSavingEntity> customerSavingEntityList=employeeDao.findPendingSavingAccountRequests();
		List<CustomerSavingForm> customerSavingFormList=new ArrayList<CustomerSavingForm>();
		if(customerSavingEntityList!=null) {
				for(CustomerSavingEntity customerSavingEntity: customerSavingEntityList){
						CustomerSavingForm customerSavingForm=new CustomerSavingForm();
						BeanUtils.copyProperties(customerSavingEntity, customerSavingForm);
						customerSavingFormList.add(customerSavingForm);
				}
		}
		return customerSavingFormList;
	}
	
	@Override
	public List<CustomerForm> findPendingSavingAccountApprovalRequests(){
		List<Customer> customerAccountApprovalList=employeeDao.findPendingSavingAccountApprovalRequests();
		List<CustomerForm> customerFormAccountApprovalList=new ArrayList<CustomerForm>();
		if(customerAccountApprovalList!=null) {
				for(Customer customer: customerAccountApprovalList){
					CustomerForm customerSavingForm=new CustomerForm();
						BeanUtils.copyProperties(customer, customerSavingForm);
						customerFormAccountApprovalList.add(customerSavingForm);
				}
		}
		return customerFormAccountApprovalList;
	}
	
	@Override
	public List<CustomerForm> findSavingApprovedAccount(){
		List<Customer> customerApprovedAccountList=employeeDao.findSavingApprovedAccount();
		List<CustomerForm> customerFormAppovedAccountList=new ArrayList<CustomerForm>();
		if(customerApprovedAccountList!=null) {
				for(Customer customer: customerApprovedAccountList){
					CustomerForm customerSavingForm=new CustomerForm();
						BeanUtils.copyProperties(customer, customerSavingForm);
						customerFormAppovedAccountList.add(customerSavingForm);
				}
		}
		return customerFormAppovedAccountList;
	}
			

	@Override
	public int findPendingSavingAccountRequestsCount() {
		return employeeDao.findPendingSavingAccountRequestsCount();
	}
	
}
