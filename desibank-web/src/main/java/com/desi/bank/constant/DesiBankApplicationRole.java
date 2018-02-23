package com.desi.bank.constant;

/**
 * 
 * @author nagendra
 *   enum is used to create the constant which can  take only predefine values
 */
public enum DesiBankApplicationRole {
	
	ADMIN("admin"),CUSTOMER("customer"),EMPLOYEE("employee");
	
	private String value;		
	private DesiBankApplicationRole(String role){
		value=role;
	}

	public String getValue(){
		return  value;
	}
	
	public static void main(String[] args) {
		DesiBankApplicationRole applicationRole=DesiBankApplicationRole.EMPLOYEE;
	}
	
}
