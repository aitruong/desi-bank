package com.desi.bank.employee.dao;

import java.util.List;

import com.desi.bank.common.dao.entity.Customer;
import com.desi.bank.common.dao.entity.CustomerSavingEntity;
import com.desi.bank.employee.dao.entity.RegistrationLinksEntity;
import com.desi.bank.employee.dao.entity.RejectSavingRequestEntity;

/**
 * 
 * @author nagendra
 *
 */
public interface EmployeeDao {

	public List<CustomerSavingEntity>  findPendingSavingAccountRequests();
	public int findPendingSavingAccountRequestsCount();
	public String rejectSavingAccountRequests(RejectSavingRequestEntity rejectSavingRequestEntity);
	public String savingApproveAccountRequests(RejectSavingRequestEntity approvalSavingRequestEntity);
	public String saveRegistrationLink(RegistrationLinksEntity linksEntity);
	public RejectSavingRequestEntity findRejectSavingRequestEntityByEmail(String email);
	public CustomerSavingEntity findCustomerSavingEnquiryById(int getCsaid);
	public List<Customer> findPendingSavingAccountApprovalRequests();
	public List<Customer> findSavingApprovedAccount();
	public String lockUnlockCustomer(String loginid, String status);

}
