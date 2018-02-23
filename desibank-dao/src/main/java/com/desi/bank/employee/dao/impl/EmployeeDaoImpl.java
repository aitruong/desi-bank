package com.desi.bank.employee.dao.impl;

import java.util.Date;
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
import com.desi.bank.common.dao.entity.Customer;
import com.desi.bank.common.dao.entity.CustomerSavingEntity;
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
	
	@Override
	public List<CustomerSavingEntity>  findPendingSavingAccountRequests() {
		List<CustomerSavingEntity>  customerSavingEntityList=(List<CustomerSavingEntity>)super.getHibernateTemplate().find("from  CustomerSavingEntity where status='"+AppDaoConstant.PENDING_STATUS+"'") ;
		return customerSavingEntityList;
	}
	
	@Override
	public List<Customer>  findPendingSavingAccountApprovalRequests() {
		List<Customer>  customerSavingEntityApprovalList=(List<Customer>)super.getHibernateTemplate().find("from  Customer where approved='"+AppDaoConstant.NO_STATUS+"'") ;
		return customerSavingEntityApprovalList;
	}
	
	@Override
	public List<Customer>  findSavingApprovedAccount() {
		List<Customer>  customerSavingEntityApprovalList=(List<Customer>)super.getHibernateTemplate().find("from  Customer where approved='"+AppDaoConstant.YES_STATUS+"'") ;
		return customerSavingEntityApprovalList;
	}
	
	@Override
	public int  findPendingSavingAccountRequestsCount() {
		List  customerSavingEntityList=super.getHibernateTemplate().find("select count(*) from  CustomerSavingEntity where status='"+AppDaoConstant.PENDING_STATUS+"'") ;
		long count=0;
		if(customerSavingEntityList!=null && customerSavingEntityList.size()==1) {
			count=(Long)customerSavingEntityList.get(0);	
		}
		return (int)count;
	}

}
