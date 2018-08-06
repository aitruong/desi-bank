package com.desi.bank.employee.dao.impl;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.ObjectNotFoundException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.desi.bank.employee.dao.EmployeeDao;
import com.desi.bank.employee.dao.entity.RejectSavingRequestEntity;

/**
 * 
 * @author nagendra
 *  This is JUnit class to test EmployeeDaoImpl
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) //We are using spring junit runner
//this line will create spring container as per xml meta configuration passed as parameter
@ContextConfiguration(inheritLocations = true, locations = {"classpath*:test-spring-context-dao.xml" })
//test-application-context.xml - dao layer,transaction manager,datasource,sessionFactory	
//this will rollback the state of the database
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class EmployeeDaoImplTest {
	
	@Autowired
	@Qualifier("EmployeeDaoImpl")
	private EmployeeDao employeeDao;

	@Test
	public void testRejectSavingAccountRequestsWhenCsaidExist() {
		//10032
		RejectSavingRequestEntity rejectSavingRequestEntity=new RejectSavingRequestEntity();
		rejectSavingRequestEntity.setCsaid(10033);
		rejectSavingRequestEntity.setCustomerName("JACKER");
		rejectSavingRequestEntity.setEmail("nagendra.synergisticit@gmail.com");
		rejectSavingRequestEntity.setReason("Document is not complete....");
		String result=employeeDao.rejectSavingAccountRequests(rejectSavingRequestEntity);
		assertNotNull(result);
		assertEquals("success", result);
		RejectSavingRequestEntity rejectSavingRequestEntity2=employeeDao.findRejectSavingRequestEntityByEmail("nagendra.synergisticit@gmail.com");
		assertNotNull(rejectSavingRequestEntity2);
		assertEquals("JACKER",rejectSavingRequestEntity.getCustomerName());
	}
	
	@Test(expected=ObjectNotFoundException.class)
	public void testRejectSavingAccountRequestsWhenCsaidDoesNotExist() {
		//10032
		RejectSavingRequestEntity rejectSavingRequestEntity=new RejectSavingRequestEntity();
		rejectSavingRequestEntity.setCsaid(920383);
		rejectSavingRequestEntity.setCustomerName("JACKER");
		rejectSavingRequestEntity.setEmail("nagendra.synergisticit@gmail.com");
		rejectSavingRequestEntity.setReason("Document is not complete....");
		employeeDao.rejectSavingAccountRequests(rejectSavingRequestEntity);
	}



}
