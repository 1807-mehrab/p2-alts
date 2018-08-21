package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Request;

public class RequestDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Request> getRequests() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Request.class).list();
		} catch (Exception e) {
			return new ArrayList<Request>();
		}
	}

	public Request getRequestById(int id) {
		Session s = sessionFactory.getCurrentSession();
		return (Request) s.get(Request.class, id);
	}

	public void addRequest(Request request) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		s.persist(request);

		tx.commit();
	}

	public void deleteRequest(Request request) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		s.delete(request);

		tx.commit();
	}

	public Request updateRequest(Request request) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		Request r = (Request) s.merge(request);

		tx.commit();

		return r;
	}
}
