package com.revature.repository;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.revature.beans.Manager;

import junit.framework.TestCase;

public class ManagerDaoTest extends TestCase {

	@Mock private SessionFactory sessionFactory;
	@Mock private Session session;
	@Mock Criteria criteria;
	@Spy ArrayList<Manager> managers = new ArrayList<Manager>();
	
	private ManagerDao dao;
	
	@BeforeClass
	protected void setUp() throws Exception {
		super.setUp();
		MockitoAnnotations.initMocks(this);
		dao = new ManagerDao();
		dao.setSessionFactory(sessionFactory);
		Mockito.doReturn(session).when(sessionFactory).getCurrentSession();
	}

	private void createManagers() {
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
