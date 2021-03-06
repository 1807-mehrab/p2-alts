package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.revature.beans.Manager;

@Repository
public class ManagerDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Manager> getManagers() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Manager.class).list();
		} catch (Exception e) {
			return new ArrayList<Manager>();
		}
	}

	public Manager getManagerById(long id) {
		Session s = sessionFactory.getCurrentSession();
		return (Manager) s.get(Manager.class, id);
	}

	public Manager getManagerByName(String firstname, String lastname) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("from Manager where fname = :fname and lname = :lname");
		q.setParameter("fname", firstname);
		q.setParameter("lname", lastname);
		return (Manager) q.uniqueResult();
	}

	public Manager getManagerByUsernameAndPassword(String username, String password) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("from Manager where username = :username and password = :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		return (Manager) q.uniqueResult();
	}

	public void addManager(Manager manager) {
		sessionFactory.getCurrentSession().persist(manager);
	}

	public void deleteManager(Manager manager) {
		sessionFactory.getCurrentSession().delete(manager);
	}

	/*
	 * * if manager is detached, it is copied upon an existing persistent entity; 
	 * * if manager is transient, it is copied upon a newly created persistent entity;
	 * 		this operation cascades for all relations with cascade=MERGE or cascade=ALL
	 * 		mapping; 
	 * * if the entity is persistent, then this method call does not have
	 * 		effect on it (but the cascading still takes place).
	 * (explanation of merge() from
	 * https://www.baeldung.com/hibernate-save-persist-update-merge-saveorupdate)
	 */
	public Manager updateManager(Manager manager) {
		return (Manager) sessionFactory.getCurrentSession().merge(manager);
	}
}