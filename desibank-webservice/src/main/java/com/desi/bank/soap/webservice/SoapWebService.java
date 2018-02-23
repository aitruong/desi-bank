package com.desi.bank.soap.webservice;

import javax.jws.WebService;

import com.desi.bank.rest.customer.Dog;

@WebService(serviceName="jackerSoapWebService")
public class SoapWebService {
	
	public Dog findDog(String did) throws FrogException{
		if(did.equals("100")){
			MessageVO messageVO=new  MessageVO();
			messageVO.setEmessage("This "+did+" is not a valid did!!!!! ");
			messageVO.setErrorCode("E100");
			FrogException exception=new FrogException("Error at provider side", messageVO);
			throw exception;
		}
		Dog dog=new Dog();
		 dog.setDid(did);
		dog.setColor("white");
		dog.setEmail("nagen@gmail.com");
		dog.setName("Amanahe 0 983");
		return dog;
	}
	
	
	public String mathOperation(int num,String operation){
				if("square".equalsIgnoreCase(operation)){
					 return "Result is = "+(num*num);
				}if("fact".equalsIgnoreCase(operation)){
					  int sum=1;
					  for(int x=2;x<=num;x++){
						  sum=sum*x;
					  }
					 return "Result is = "+(sum);
				}else{
					 return "operation is not supported";
				}
	}

}
