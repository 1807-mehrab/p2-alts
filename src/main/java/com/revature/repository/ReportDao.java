package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Report;

public class ReportDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Report> getReports() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Report.class).list();
		} catch (Exception e) {
			return new ArrayList<Report>();
		}
	}

	public Report getReportById(int id) {
		Session s = sessionFactory.getCurrentSession();
		return (Report) s.get(Report.class, id);
	}

	public void addReport(Report report) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		s.persist(report);

		tx.commit();
	}

	public void deleteReport(Report report) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		s.delete(report);

		tx.commit();
	}

	public Report updateClient(Report report) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		Report r = (Report) s.merge(report);

		tx.commit();

		return r;
	}
}
