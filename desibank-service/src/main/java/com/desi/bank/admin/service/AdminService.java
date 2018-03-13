package com.desi.bank.admin.service;

import java.util.List;

import com.desi.bank.customer.web.controller.form.CustomerForm;

/**
 * 
 * @author nagendra.yadav
 *
 */
public interface AdminService {
	public List<CustomerForm> listUnapprovedCustomers();
	public String approveCustomers(String[] id );
	boolean addDefaultAccountNumber(long id);
	public String lockCustomers(String[] id );
	public List<CustomerForm> listUnlockedCustomers();
	public String unlockCustomers(String[] id );
	public List<CustomerForm> listlockedCustomers();
	public List<CustomerForm> showCustomers();
	public String changePasword(String userid,String password);
	public boolean addDefaultTransactionID(long id);
	public List<CustomerForm> listPaginatedCustomers(int page);
	public List<CustomerForm> searchUnapprovedCustomers(String keyword);
}
