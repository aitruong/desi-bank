package com.desi.bank.rest.api.vo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class CustomerSavingVOList {
	
	private List<CustomerSavingVO> customerSavingVOs=new ArrayList<CustomerSavingVO>();
	
	
	public List<CustomerSavingVO> getCustomerSavingVOs() {
		return customerSavingVOs;
	}

	public void setCustomerSavingVOs(List<CustomerSavingVO> customerSavingVOs) {
		this.customerSavingVOs = customerSavingVOs;
	}

	@Override
	public String toString() {
		return "CustomerSavingVOList [customerSavingVOs=" + customerSavingVOs + "]";
	}
	
	

}
