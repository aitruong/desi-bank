package com.desi.bank.customer.service;

import java.util.List;

import com.desi.bank.customer.web.controller.form.PayeeModel;

/**
 * @author VC1
 * 
 *
 */
public interface CustomerPayeeService {

	public String addPayee(PayeeModel payee);
	public List<PayeeModel> getPayeeList(String loginid);

}
