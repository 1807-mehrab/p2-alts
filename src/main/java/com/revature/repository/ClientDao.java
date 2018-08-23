package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

	public Client getClientById(long id) {
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

	public Client getByUsernameAndPassword(String username, String password) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("from Client where username = :username and password = :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		return (Client) q.uniqueResult();
	}

	public void addClient(Client client) {
		sessionFactory.getCurrentSession().persist(client);
	}

	public void deleteClient(Client client) {
		sessionFactory.getCurrentSession().delete(client);
	}

	public Client updateClient(Client client) {
		return (Client) sessionFactory.getCurrentSession().merge(client);
	}
}