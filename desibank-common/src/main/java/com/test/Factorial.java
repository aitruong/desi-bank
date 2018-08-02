package com.test;

/**
 * 
 * @author nagendra
 *
 */
public class Factorial {

	private int num;
	public Factorial(){
	}
	
	public Factorial(int num) {
		this.num = num;
	}
	
	/**
	 *  5! = 1*2*3*4*5
	 *  6! = 1*2*3*4*5*6
	 *  0!= 1
	 *  1! = 1
	 *  2! = 1*2
	 *  3!= 1*2.*3
	 *  computing factorial of a number
	 * @return int value
	 */
	public int calFact() {
		if(this.num<0){
			RuntimeException exception=new RuntimeException("Hey number cannot be negative to compute fact");
			throw exception;
		}
		int sum=1;
		for(int x=2;x<=num;x++) {
			sum=sum*x;
		}
		return sum;
	}
	
	
	
	
}
