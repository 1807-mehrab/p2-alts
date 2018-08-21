package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Employee;

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

	public Employee getEmployeeById(int id) {
		Session s = sessionFactory.getCurrentSession();
		return (Employee) s.get(Employee.class, id);
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