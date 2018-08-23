package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.revature.beans.Report;

@Repository
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

	public Report getReportById(long id) {
		Session s = sessionFactory.getCurrentSession();
		return (Report) s.get(Report.class, id);
	}

	public Report getReportByTitle(String title) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("from Report where title = :title");
		q.setParameter("title", title);
		return (Report) q.uniqueResult();
	}

	public void addReport(Report report) {
		sessionFactory.getCurrentSession().persist(report);
	}

	public void deleteReport(Report report) {
		sessionFactory.getCurrentSession().delete(report);
	}

	public Report updateReport(Report report) {
		return (Report) sessionFactory.getCurrentSession().merge(report);
	}
}
