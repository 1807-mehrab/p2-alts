package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

	public Manager getManagerById(int id) {
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

	public void addManager(Manager manager) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		s.persist(manager);

		tx.commit();
	}

	public void deleteManager(Manager manager) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		s.delete(manager);

		tx.commit();
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
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		Manager m = (Manager) s.merge(manager);

		tx.commit();

		return m;
	}
}