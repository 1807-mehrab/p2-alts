package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Manager;
import com.revature.repository.ManagerDao;

@Service
public class ManagerService {
	@Autowired
	ManagerDao dao;

	public void setDao(ManagerDao dao) {
		this.dao = dao;
	}

	@Transactional
	public List<Manager> getAll() {
		return dao.getManagers();
	}
}