package com.test;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
	/*public static void main(String[] args) {
		//System.out.println(generateFebb(7));
	}
*/
	 public  List<Integer> generateFebb(int num) throws RuntimeException {
		 List<Integer> arr=new ArrayList<Integer>();
		 if(num==1000){
			 try {
				 Thread.sleep(3000);
				 arr.add(1);
				 arr.add(10);
				 arr.add(100);
				 arr.add(1000);
				 arr.add(10000);
			 }catch(Exception ex){
			 }
			 return arr;
		 }
		 
		 if(num>=6000){
			 RuntimeException ex=new RuntimeException("Sorry we cannot compute fibbonacci series of 6000+");
			throw ex;
		 }
		  if(num<=0) {
		     return  arr;	 
		 }else if(num==1){
			 arr.add(0);
			 return  arr;	 
		 }
		 else if(num==2){
			 arr.add(0);
			 arr.add(1);
			 return  arr;	 
		 }
	
		arr.add(0);
		arr.add(1);
		for (int i =3; i <= num; i++) {
			arr.add(arr.get(i-3)+arr.get(i-2));
		}
		return arr;
	}

}
