package com.desi.bank.data.util;
/*package com.spring.util;

public class AccountNumGenerator {
	
	private static AccountNumGenerator ang = null;
	 private String num1 = "00310";
	 private long num2 = 1838;
	 private long num3 = 9999;
	
	 private AccountNumGenerator(){ }
	 
	public String getNum1() {
		return num1;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}

	public long getNum2() {
		return num2;
	}

	public void setNum2(long num2) {
		this.num2 = num2;
	}

	public long getNum3() {
		return num3;
	}

	public void setNum3(long num3) {
		this.num3 = num3;
	}

	
	public synchronized static AccountNumGenerator getInstance(){
		
		if(ang == null)
			ang = new AccountNumGenerator();
		
		return ang;	
	}
	
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}
	
	public String getAccNum(){
		StringBuilder sb = new StringBuilder();
		 sb.append(num1).append(num2++).append(num3++);
		 
		
		return sb.toString();
	}
	

}


*/