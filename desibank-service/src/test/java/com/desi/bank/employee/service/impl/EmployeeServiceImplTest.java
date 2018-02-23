package com.desi.bank.employee.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.desi.bank.common.dao.entity.CustomerSavingEntity;
import com.desi.bank.customer.web.controller.form.CustomerSavingForm;
import com.desi.bank.employee.dao.impl.EmployeeDaoImpl;
/**
 *  
 *  
 */
//we are using junit runner from mockito because we have to use feature of mockito in my test cases
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {
	
	//we are creating mock object for IUserDao
	@Mock
	private EmployeeDaoImpl employeeDaoImpl;
	
	//new IUserService();
	@InjectMocks //this is creating an object of IUserService and injecting it;s all dependent objects as mock
	private EmployeeServiceImpl employeeServiceImpl;
	
	
	@Test
	public void testFindPendingSavingAccountRequestsWhenSizeZero() {
		List<CustomerSavingEntity> customerSavingEntityList=new ArrayList<CustomerSavingEntity>();
		//Stubbing  the behavior
		when(employeeDaoImpl.findPendingSavingAccountRequests()).thenReturn(customerSavingEntityList);
		List<CustomerSavingForm> customerSavingForms=employeeServiceImpl.findPendingSavingAccountRequests();
		 assertNotNull(customerSavingForms);
		 assertEquals(0, customerSavingForms.size());
	}

	@Test
	public void testFindPendingSavingAccountRequestsWhenNotSize2() {
		List<CustomerSavingEntity> customerSavingEntityList=new ArrayList<CustomerSavingEntity>();
		CustomerSavingEntity customerSavingEntity1=new CustomerSavingEntity();
		customerSavingEntity1.setCsaid(1000);
		customerSavingEntity1.setEmail("nagen@gmail.com");
		customerSavingEntity1.setLocation("Fremont");
		customerSavingEntity1.setMobile("+01929298282");
		customerSavingEntity1.setName("Nagen");
		customerSavingEntity1.setStatus("PENDING");
		
		CustomerSavingEntity customerSavingEntity2=new CustomerSavingEntity();
		customerSavingEntity2.setCsaid(2000);
		customerSavingEntity2.setEmail("nagendra@gmail.com");
		customerSavingEntity2.setLocation("Losuey");
		customerSavingEntity2.setMobile("+01234234");
		customerSavingEntity2.setName("Robert");
		customerSavingEntity2.setStatus("APPROVED");
		customerSavingEntityList.add(customerSavingEntity1);
		customerSavingEntityList.add(customerSavingEntity2);
		//Stubbing  the behavior
		when(employeeDaoImpl.findPendingSavingAccountRequests()).thenReturn(customerSavingEntityList);
		
		List<CustomerSavingForm> customerSavingForms=employeeServiceImpl.findPendingSavingAccountRequests();
		 assertNotNull(customerSavingForms);
		 assertEquals(2, customerSavingForms.size());
		 assertEquals("nagendra@gmail.com",customerSavingForms.get(1).getEmail());
		 assertEquals("Robert",customerSavingForms.get(1).getName());
	}

	@Test
	@Ignore
	public void testRejectSavingAccountRequests() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testSavingApproveAccountRequests() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testSaveRegistrationLink() {
		fail("Not yet implemented");
	}


	@Test
	@Ignore
	public void testFindPendingSavingAccountRequestsCount() {
		fail("Not yet implemented");
	}

}
