package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.revature.beans.Request;

@Repository
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

	public Request getRequestById(long id) {
		Session s = sessionFactory.getCurrentSession();
		return (Request) s.get(Request.class, id);
	}

	public void addRequest(Request request) {
		sessionFactory.getCurrentSession().persist(request);
	}

	public void deleteRequest(Request request) {
		sessionFactory.getCurrentSession().delete(request);
	}

	public Request updateRequest(Request request) {
		return (Request) sessionFactory.getCurrentSession().merge(request);
	}
}
