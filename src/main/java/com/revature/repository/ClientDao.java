package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.beans.Client;

@Repository
public class ClientDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Client> getClients() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Client.class).list();
		} catch (Exception e) {
			return new ArrayList<Client>();
		}
	}

	public Client getCustomerById(int id) {
		Session s = sessionFactory.getCurrentSession();
		return (Client) s.get(Client.class, id);
	}

	public Client getClientByName(String firstname, String lastname) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("from Client where fname = :fname and lname = :lname");
		q.setParameter("fname", firstname);
		q.setParameter("lname", lastname);
		return (Client) q.uniqueResult();
	}

	public void addClient(Client client) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		s.persist(client);

		tx.commit();
	}

	public void deleteClient(Client client) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		s.delete(client);

		tx.commit();
	}

	public Client updateClient(Client client) {
		Session s = sessionFactory.getCurrentSession();
		Transaction tx = s.beginTransaction();

		Client c = (Client) s.merge(client);

		tx.commit();

		return c;
	}
}