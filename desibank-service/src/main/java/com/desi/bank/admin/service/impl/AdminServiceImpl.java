package com.desi.bank.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desi.bank.admin.dao.AdminDao;
import com.desi.bank.admin.service.AdminService;
import com.desi.bank.customer.web.controller.form.CustomerForm;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public List<CustomerForm> listUnapprovedCustomers() {
		return adminDao.listUnapprovedCustomers();
	}

	@Override
	public String approveCustomers(String[] id) {
		return adminDao.approveCustomers(id);
	}
	
	@Override
	public boolean addDefaultAccountNumber(long id){
		return	adminDao.addDefaultAccountNumber(id);
	}

	@Override
	public String lockCustomers(String[] id) {
		return adminDao.lockCustomers(id);
	}

	@Override
	public List<CustomerForm> listUnlockedCustomers() {
		
		return adminDao.listUnlockedCustomers();
	}

	@Override
	public String unlockCustomers(String[] id) {
		return adminDao.unlockCustomers(id);
	}

	@Override
	public List<CustomerForm> listlockedCustomers() {
		return adminDao.listLockedCustomers();
	}

	@Override
	public List<CustomerForm> showCustomers() {
		return adminDao.showCustomers();
	}

	@Override
	public String changePasword(String userid, String password) {
		return adminDao.changePassword(userid, password);
	}

	@Override
	public boolean addDefaultTransactionID(long id) {
		return adminDao.addDefaultTransactionID(id);
	}

	@Override
	public List<CustomerForm> listPaginatedCustomers(int page) {
		// TODO Auto-generated method stub
		return adminDao.listPaginatedCustomers(page);
	}

	@Override
	public List<CustomerForm> searchUnapprovedCustomers(String keyword) {
		// TODO Auto-generated method stub
		return adminDao.searchUnapprovedCustomers(keyword);
	}

}
