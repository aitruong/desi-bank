package com.desi.bank.rest.employee;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.desi.bank.rest.customer.Dog;


//http://localhost:838/desi-bank/v1/payment/dog?did=100
@Component
@Path("/payment")
@Scope("request")
public class PaymentService {
	
	@Path("/dog")
	@GET
	@Produces("application/json")
	public Dog findDog(@QueryParam("did") String did){
		Dog dog=new Dog();
		dog.setColor("white");
		dog.setEmail("nagen@gmail.com");
		dog.setName("Amanahe 0 983");
		return dog;
	}


	@GET
	@Path("/test")
	public Response savePayment() {
		return Response.status(200).entity("testing rest with jersey").build();
	}

}