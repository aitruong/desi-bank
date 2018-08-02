package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class FactorialTest {
	
	//10 test case - then JUNit will create 10 instance of FactorialTest classs
	public FactorialTest() {
		//so this constructor will be called 10 times!
		System.out.println("(#*@FactorialTest*@*@*");
	}
	
	
	@BeforeClass
	public void paoa(){
		System.out.println(")@#)#)#)Only once");
	}
	
	
	@Before
	public void cheers(){
		System.out.println("Welcome !!!!!!!!!!!!!");
	}

	@Test
	public void testCalFactWhenInputIsZero() {
		Factorial factorial=new Factorial(0);
		int actual=factorial.calFact();
		assertEquals(1, actual);
	}
	
	@Test
	public void testCalFactWhenInputIsOne() {
		Factorial factorial=new Factorial(1);
		int actual=factorial.calFact();
		assertEquals(1, actual);
	}
	
	@Test
	public void testCalFactWhenInputIsThree() {
		Factorial factorial=new Factorial(3);
		int actual=factorial.calFact();
		assertEquals(6, actual);
	}
	
	@Test
	public void testCalFactWhenInputIsSix() {
		Factorial factorial=new Factorial(6);
		int actual=factorial.calFact();
		assertEquals(720, actual);
	}
	
	@Test(expected=RuntimeException.class)
	public void testCalFactWhenInputIsNegative() {
		Factorial factorial=new Factorial(-2);
		factorial.calFact();
	}
	
	@Ignore
	@Test
	public void testCalFactWhenInputIsMax() {
		
	}

}
