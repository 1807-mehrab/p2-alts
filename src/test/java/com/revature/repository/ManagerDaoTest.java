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

	@Mock
	private SessionFactory sessionFactory;
	@Mock
	private Session session;
	@Mock
	Criteria criteria;
	@Spy
	ArrayList<Manager> managers = new ArrayList<Manager>();

	private ManagerDao dao;

	@BeforeClass
	protected void setUp() throws Exception {
		super.setUp();
		MockitoAnnotations.initMocks(this);
		dao = new ManagerDao();
		dao.setSessionFactory(sessionFactory);
		managers = createManagers();
		Mockito.doReturn(session).when(sessionFactory).getCurrentSession();
	}

	private ArrayList<Manager> createManagers() {
		Manager m = new Manager();
		ArrayList<Manager> temp = new ArrayList<Manager>();

		m.setClient_id(1);
		m.setEmail("jubydoo@gmail.com");
		m.setFname("Andrew");
		m.setLname("Juby");
		m.setUsername("jubydoo");
		m.setPassword("password");
		temp.add(m);

		m.setClient_id(2);
		m.setEmail("harry.potter@hogwarts.edu");
		m.setFname("Harry");
		m.setLname("Potter");
		m.setUsername("hpotter");
		m.setPassword("password");
		temp.add(m);

		m.setClient_id(3);
		m.setEmail("andrew.wiggins@fleet.gov");
		m.setFname("Andrew");
		m.setLname("Wiggins");
		m.setUsername("ender");
		m.setPassword("password");
		temp.add(m);

		return null;
	}
	
	@Test
	public void testGetAll() {
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
