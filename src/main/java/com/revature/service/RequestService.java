package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Request;
import com.revature.repository.RequestDao;

@Service
public class RequestService {
	@Autowired
	RequestDao dao;

	public void setDao(RequestDao dao) {
		this.dao = dao;
	}

	@Transactional
	public List<Request> getAll() {
		return dao.getRequests();
	}

	@Transactional
	public void add(Request request) {
		dao.addRequest(request);
	}

	@Transactional
	public void delete(Request request) {
		dao.deleteRequest(request);
	}

	@Transactional
	public Request getById(int id) {
		return dao.getRequestById(id);
	}

	@Transactional
	public Request update(Request request) {
		return dao.updateRequest(request);
	}
}