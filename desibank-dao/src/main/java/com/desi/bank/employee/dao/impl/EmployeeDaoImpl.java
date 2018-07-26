package com.desi.bank.employee.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.desi.bank.common.dao.AppDaoConstant;
import com.desi.bank.common.dao.entity.AccountNumberGenerator;
import com.desi.bank.common.dao.entity.Customer;
import com.desi.bank.common.dao.entity.CustomerAccountInfo;
import com.desi.bank.common.dao.entity.CustomerSavingEntity;
import com.desi.bank.common.dao.entity.Login;
import com.desi.bank.constant.DesiBankApplicationRole;
import com.desi.bank.constant.DesiBankConstant;
import com.desi.bank.employee.dao.EmployeeDao;
import com.desi.bank.employee.dao.entity.RegistrationLinksEntity;
import com.desi.bank.employee.dao.entity.RejectSavingRequestEntity;

@Repository("EmployeeDaoImpl")
@Scope("singleton")
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {

	@Autowired
	@Qualifier("sessionFactory")
	public void setSpringManageSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
		
	}
	
	/**
	 *  This is should be in the transaction......................
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public CustomerAccountInfo  createCustomerAccount(String userid) {
		//Step - Loading the customer detail
		//userid is not a primary key ..it is a candidate key!
		
		//loading the customer details
		List<Customer> cutomers=(List<Customer>)super.getHibernateTemplate().find("from  Customer where userid=?",userid);
		
		Customer customer;
		if(cutomers!=null && cutomers.size()==1){
			customer=cutomers.get(0);
			customer.setApproved("yes");
		}else{
			RuntimeException exception=new RuntimeException("duplicate userid is found for selected customer  "+userid);
			throw exception;
		}
		//write logic to generate account number
		AccountNumberGenerator accountNumberGenerator=super.getHibernateTemplate().get(AccountNumberGenerator.class, 1L);
		long newAccountNumber =accountNumberGenerator.getAccountNumber()+1;
		//change the state of 
		accountNumberGenerator.setAccountNumber(newAccountNumber);
		//Creating customer account details
		CustomerAccountInfo customerAccountInfo=new CustomerAccountInfo();
		customerAccountInfo.setAccountNumber(DesiBankConstant.PREFIX_ACCOUNT_NUMBER+""+newAccountNumber);
		customerAccountInfo.setAccountType("Saving");
		customerAccountInfo.setAvBalance(10000.00F);
		customerAccountInfo.setBranch("Fremont");
		customerAccountInfo.setCurrency("$");
		customerAccountInfo.setCustomerId(userid);
		customerAccountInfo.setStatusAsOf(new Date());
		customerAccountInfo.setTavBalance(10000.00F);
		super.getHibernateTemplate().save(customerAccountInfo);
		super.getHibernateTemplate().update(accountNumberGenerator);
		super.getHibernateTemplate().update(customer);
		return customerAccountInfo;
	}
	
	
	/**
	 *  This is should be in the transaction......................
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public String  rejectSavingAccountRequests(RejectSavingRequestEntity rejectSavingRequestEntity) {
		CustomerSavingEntity customerSavingEntity=super.getHibernateTemplate().load(CustomerSavingEntity.class,rejectSavingRequestEntity.getCsaid() );
		rejectSavingRequestEntity.setDoa(new Date());
		rejectSavingRequestEntity.setLocation(customerSavingEntity.getLocation());
		rejectSavingRequestEntity.setMobile(customerSavingEntity.getMobile());
		super.getHibernateTemplate().delete(customerSavingEntity);
		super.getHibernateTemplate().save(rejectSavingRequestEntity);
		return "success";
	}
	
	/**
	 *  This is should be in the transaction......................
	 */
	@Override
	public CustomerSavingEntity  findCustomerSavingEnquiryById(int getCsaid) {
		CustomerSavingEntity customerSavingEntity =super.getHibernateTemplate().get(CustomerSavingEntity.class, getCsaid);
		return customerSavingEntity;
	}
	
	/**
	 *  This is should be in the transaction......................
	 */
	@Override
	public RejectSavingRequestEntity  findRejectSavingRequestEntityByEmail(String email) {
		List<RejectSavingRequestEntity>  rejectSavingRequestEntities=(List<RejectSavingRequestEntity>)super.getHibernateTemplate().find("from  RejectSavingRequestEntity where email=?",email);
		return rejectSavingRequestEntities.get(0);
	}
	
	/**
	 *  This is should be in the transaction......................
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public String  savingApproveAccountRequests(RejectSavingRequestEntity approvalSavingRequestEntity) {
		CustomerSavingEntity customerSavingEntity=super.getHibernateTemplate().load(CustomerSavingEntity.class,approvalSavingRequestEntity.getCsaid() );
		customerSavingEntity.setStatus(AppDaoConstant.APPROVED_STATUS);
		//IOException  ->> it will --- -Only for Runtime 
		return "success";
	}
	
	/**
	 *  This is should be in the transaction......................
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public String  saveRegistrationLink(RegistrationLinksEntity linksEntity) {
		super.getHibernateTemplate().save(linksEntity);
		return "success";
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public String  lockUnlockCustomer(String loginid,String status) {
		String resultStatus="success";
		List<Login>  loginList=(List<Login>)super.getHibernateTemplate().find("from  Login where loginid='"+loginid+"'") ;
		if(loginList!=null && loginList.size()==1){
			   if("lock".equals(status)){
				   loginList.get(0).setLocked("yes");
			   }else if("unlock".equals(status)){
				   loginList.get(0).setLocked("no");
			   }else{
					resultStatus="fail";
			   }
		}else{
			resultStatus="fail";
		}
		return resultStatus;
	}
	//EmployeeDaoImpl.java
	@Override
	public List<CustomerSavingEntity>  findPendingSavingAccountRequests() {
		List<CustomerSavingEntity>  customerSavingEntityList=(List<CustomerSavingEntity>)super.getHibernateTemplate().find("from  CustomerSavingEntity where status='"+AppDaoConstant.PENDING_STATUS+"'") ;
		return customerSavingEntityList;
	}
	
	@Override
	public List<Customer>  findPendingSavingAccountApprovalRequests() {
		List<Customer>  customerSavingEntityApprovalList=(List<Customer>)super.getHibernateTemplate().find("from  Customer where approved='"+AppDaoConstant.NO_STATUS+"' OR approved='0'") ;
		return customerSavingEntityApprovalList;
	}
	
	@Override
	public List<Customer>  findSavingApprovedAccount() {
		List<Customer>  customerSavingEntityApprovalList=(List<Customer>)super.getHibernateTemplate().find("from  Customer where   approved='"+AppDaoConstant.YES_STATUS+"'") ;
		 Iterator<Customer>  it=customerSavingEntityApprovalList.iterator();
		while(it.hasNext()){
			Customer customer=it.next();
			List<Login>  loginEntityList=(List<Login>)super.getHibernateTemplate().find("from  Login  where loginid='"+customer.getUserid()+"'") ;
			if(loginEntityList!=null && loginEntityList.size()==1){
				if(DesiBankApplicationRole.CUSTOMER.getValue().equalsIgnoreCase(loginEntityList.get(0).getRole())){
					if(loginEntityList.get(0).getLocked().equalsIgnoreCase(AppDaoConstant.NO_STATUS)) {
							customer.setIslocked(false);
					}
					else {
						customer.setIslocked(true);
					}		
				}else{
					it.remove();
				}
			}
		}
		return customerSavingEntityApprovalList;
	}
	
	@Override
	public int  findPendingSavingAccountRequestsCount() { //HQL
		List  customerSavingEntityList=super.getHibernateTemplate().find("select count(*) from  CustomerSavingEntity where status='"+AppDaoConstant.PENDING_STATUS+"'") ;
		long count=0;
		if(customerSavingEntityList!=null && customerSavingEntityList.size()==1) {
			count=(Long)customerSavingEntityList.get(0);	
		}
		return (int)count;
	}

}
