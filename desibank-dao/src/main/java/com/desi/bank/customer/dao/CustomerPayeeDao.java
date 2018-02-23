package com.desi.bank.customer.dao;

import java.util.List;

import com.desi.bank.common.dao.entity.PayeeInfo;

/**
 * 
 * @author VC1
 *
 */
public interface CustomerPayeeDao {
	public String addPayee(PayeeInfo payee);
	public List<PayeeInfo> getPayeeList(String userid);
	
}
