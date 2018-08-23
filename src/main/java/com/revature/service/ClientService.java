package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Client;
import com.revature.repository.ClientDao;

@Service
public class ClientService {
	@Autowired
	ClientDao dao;

	public void setDao(ClientDao dao) {
		this.dao = dao;
	}

	@Transactional
	public List<Client> getAll() {
		return dao.getClients();
	}

	@Transactional
	public void add(Client client) {
		dao.addClient(client);
	}

	@Transactional
	public void delete(Client client) {
		dao.deleteClient(client);
	}

	@Transactional
	public Client getById(long id) {
		return dao.getClientById(id);
	}

	@Transactional
	public Client getByName(String fname, String lname) {
		return dao.getClientByName(fname, lname);
	}

	@Transactional
	public Client update(Client client) {
		return dao.updateClient(client);
	}
}