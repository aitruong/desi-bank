package com.desi.bank.admin.dao;

import java.util.List;

import com.desi.bank.customer.web.controller.form.CustomerForm;

public interface AdminDao {

	public List<CustomerForm> listUnapprovedCustomers();
	public String approveCustomers(String[] id );
	boolean addDefaultAccountNumber(long id);
	public List<CustomerForm> listLockedCustomers();
	public String lockCustomers(String[] id);
	public List<CustomerForm> listUnlockedCustomers();
	public String unlockCustomers(String[] id);
	public List<CustomerForm> showCustomers();
	public String changePassword(String userid,String password);
	boolean addDefaultTransactionID(long id);
	public List<CustomerForm> listPaginatedCustomers(int page);
	public List<CustomerForm> searchUnapprovedCustomers(String keyword);
	
}
