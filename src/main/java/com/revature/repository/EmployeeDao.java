package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.beans.Employee;

@Repository
public class EmployeeDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Employee.class).list();
		} catch (Exception e) {
			return new ArrayList<Employee>();
		}
	}

	public Employee getEmployeeById(long id) {
		Session s = sessionFactory.getCurrentSession();
		return (Employee) s.get(Employee.class, id);
	}
	
	public Employee getEmployeeByName(String firstname, String lastname) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("from Employee where fname = :fname and lname = :lname");
		q.setParameter("fname", firstname);
		q.setParameter("lname", lastname);
		return (Employee) q.uniqueResult();
	}

	public void addEmployee(Employee employee) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		s.persist(employee);

		tx.commit();
	}

	public void deleteEmployee(Employee employee) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		s.delete(employee);

		tx.commit();
	}

	public Employee updateEmployee(Employee employee) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		Employee e = (Employee) s.merge(employee);

		tx.commit();

		return e;
	}
}