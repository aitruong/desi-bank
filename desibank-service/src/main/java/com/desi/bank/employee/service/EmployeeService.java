package com.desi.bank.employee.service;

import java.util.List;

import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.customer.web.controller.form.CustomerSavingForm;
import com.desi.bank.employee.web.controller.form.RegistrationLinksForm;
import com.desi.bank.employee.web.controller.form.RejectSavingRequestForm;

/**
 * 
 * @author nagendra
 *
 */
public interface EmployeeService {

	public abstract List<CustomerSavingForm> findPendingSavingAccountRequests();
	public int  findPendingSavingAccountRequestsCount() ;
	public String rejectSavingAccountRequests(RejectSavingRequestForm customerSavingForm);
	public String savingApproveAccountRequests(RejectSavingRequestForm approvalSavingRequestForm);
	public String saveRegistrationLink(RegistrationLinksForm registrationLinksForm);
	public List<CustomerForm> findPendingSavingAccountApprovalRequests();
	public List<CustomerForm> findSavingApprovedAccount();
	public String lockUnlockCustomer(String loginid, String status);
}
