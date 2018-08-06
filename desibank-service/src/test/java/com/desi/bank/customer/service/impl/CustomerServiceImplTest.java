package com.desi.bank.customer.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.desi.bank.common.dao.entity.CustomerSavingEntity;
import com.desi.bank.customer.dao.impl.CustomerDaoImpl;
import com.desi.bank.customer.web.controller.form.CustomerForm;
import com.desi.bank.customer.web.controller.form.CustomerSavingForm;

//Hey I am go to run this test using Mockito JUnit Runner
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {
	
	
	//we are creating mock object for CustomerDaoImpl
	@Mock
	private CustomerDaoImpl customerDaoImpl;
	
	
	
	//new CustomerServiceImpl();
	@InjectMocks //this is creating an object of IUserService and injecting it;s all dependent objects as mock
	private CustomerServiceImpl customerServiceImpl;
	
	
	@Test
	public void testFindCustomerSavingEnquiryByEmail() {
		CustomerSavingEntity customerSavingEntity=new CustomerSavingEntity();
		customerSavingEntity.setCsaid(102992);
		customerSavingEntity.setDoa(new Date());
		customerSavingEntity.setEmail("oueyete@gmail.com");
		customerSavingEntity.setLocation("Fremont 192");
		customerSavingEntity.setMobile("02928928282");
		customerSavingEntity.setName("Nagendra Kumar");
		customerSavingEntity.setStatus("Pending");
		when(customerDaoImpl.findCustomerSavingEnquiryByEmail("oueyete@gmail.com")).thenReturn(customerSavingEntity);
		
		CustomerSavingForm customerSavingForm=customerServiceImpl.findCustomerSavingEnquiryByEmail("oueyete@gmail.com");
		assertNotNull(customerSavingForm);
		assertEquals(102992,customerSavingForm.getCsaid());
		assertEquals("oueyete@gmail.com",customerSavingForm.getEmail());
		assertEquals("Nagendra Kumar",customerSavingForm.getName());
		assertEquals("Pending",customerSavingForm.getStatus());
	}

	@Test
	public void testFindCustomersWhenListIsNull() {
		//Stubbing  the behavior or mocking the behavior of the dao layer
		//which will be called by the service layer internally
		 when(customerDaoImpl.findCustomers()).thenReturn(null);
		List<CustomerForm> dcustomerForms=customerServiceImpl.findCustomers();
		assertNull(dcustomerForms);
	}
	

	@Test
	public void testFindCustomersWhenListSizeZero() {
		List<CustomerForm> customerForms=new ArrayList<CustomerForm>();
		//Stubbing  the behavior or mocking the behavior of the dao layer
		//which will be called by the service layer internally
		 when(customerDaoImpl.findCustomers()).thenReturn(customerForms);
		List<CustomerForm> dcustomerForms=customerServiceImpl.findCustomers();
		assertNotNull(dcustomerForms);
		assertEquals(0, dcustomerForms.size());
		
	}
	
	@Test
	public void testFindCustomers() {
		List<CustomerForm> customerForms=new ArrayList<CustomerForm>();
		CustomerForm customerForm1=new CustomerForm();
		customerForm1.setAccountNum("9202827272");
		customerForm1.setEmail("nagen@gmail.com");
		customerForm1.setGender("Male");
		customerForm1.setName("Nagendra");
		customerForm1.setUserid("jacker202");
		
		CustomerForm customerForm2=new CustomerForm();
		customerForm2.setAccountNum("93288383");
		customerForm2.setEmail("ameya@gmail.com");
		customerForm2.setGender("Female");
		customerForm2.setName("Ameya");
		customerForm2.setUserid("ameya822");
		customerForms.add(customerForm1);
		customerForms.add(customerForm2);
		//Stubbing  the behavior or mocking the behavior of the dao layer
		//which will be called by the service layer internally
		 when(customerDaoImpl.findCustomers()).thenReturn(customerForms);
		List<CustomerForm> dcustomerForms=customerServiceImpl.findCustomers();
		assertNotNull(dcustomerForms);
		assertEquals(2, dcustomerForms.size());
		assertEquals("9202827272",dcustomerForms.get(0).getAccountNum());
		assertEquals("nagen@gmail.com",dcustomerForms.get(0).getEmail());
		assertEquals("jacker202",dcustomerForms.get(0).getUserid());
		
		assertEquals("93288383",dcustomerForms.get(1).getAccountNum());
		assertEquals("ameya@gmail.com",dcustomerForms.get(1).getEmail());
		assertEquals("ameya822",dcustomerForms.get(1).getUserid());
		
	}
	

	@Test
	@Ignore
	public void testSavingAccountRequest() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testUpdatePassword() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testValidateCustomerByUserId() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testFindCustomersExpirePassWithInSevenDays() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testPersistCustomer() {
		fail("Not yet implemented");
	}


	@Ignore
	@Test
	public void testFindImageByUserid() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testValidateCustomer() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testDeleteCustomer() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetCustomerInt() {
		fail("Not yet implemented");
	}

	
	@Ignore
	@Test
	public void testGetCustomerString() {
		fail("Not yet implemented");
	}

	
	@Ignore
	@Test
	public void testUpdateCustomer() {
		fail("Not yet implemented");
	}
	
	
	
	@Ignore
	@Test
	public void testGetAccountDetails() {
		fail("Not yet implemented");
	}

	
	@Ignore
	@Test
	public void testGetAccount() {
		fail("Not yet implemented");
	}

	
	@Ignore
	@Test
	public void testGetTransactionDetails() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testPersistCustomerTransaction() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetUserDetail() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testGetSecurityQn() {
		fail("Not yet implemented");
	}
	
	
	
	@Ignore
	@Test
	public void testCheckPassword() {
		fail("Not yet implemented");
	}

	
	@Ignore
	@Test
	public void testFindEmailByUserid() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testFindLinkDetailByCuid() {
		fail("Not yet implemented");
	}

	

	@Ignore
	@Test
	public void testScheduledTransfer() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testScheduledCustomerTransaction() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testUpdateScheduledTransaction() {
		fail("Not yet implemented");
	}

}
