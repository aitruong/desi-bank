package com.desi.bank.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.desi.bank.common.dao.entity.PayeeInfo;
import com.desi.bank.customer.dao.CustomerPayeeDao;
import com.desi.bank.customer.service.CustomerPayeeService;
import com.desi.bank.customer.web.controller.form.PayeeModel;

/**
 * 
 * @author VC1
 *
 */
@Service("ICustomerPayeeService")
@Scope("singleton")
public class ICustomerPayeeService implements CustomerPayeeService {
	
	@Autowired
	private CustomerPayeeDao customerPayeeDao;
	
	@Override
	public String addPayee(PayeeModel payee) {
		PayeeInfo payeeInfo=new PayeeInfo();
		  BeanUtils.copyProperties(payee,payeeInfo);
		  return customerPayeeDao.addPayee(payeeInfo);
	}

	@Override
	public List<PayeeModel> getPayeeList(String loginid) {
		List<PayeeInfo> listPayee=customerPayeeDao.getPayeeList(loginid);
		List<PayeeModel> payeeModel= new ArrayList();
		for(PayeeInfo payee :listPayee)
		{
		PayeeModel payeeMod=new PayeeModel();
		BeanUtils.copyProperties(payee,payeeMod);
		payeeModel.add(payeeMod);
		}
		return payeeModel;
	}
	

}
