package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Employee;
import com.revature.repository.EmployeeDao;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDao dao;

	public void setDao(EmployeeDao dao) {
		this.dao = dao;
	}

	@Transactional
	public List<Employee> getAll() {
		return dao.getEmployees();
	}

	@Transactional
	public void add(Employee employee) {
		dao.addEmployee(employee);
	}

	@Transactional
	public void delete(Employee employee) {
		dao.deleteEmployee(employee);
	}

	@Transactional
	public Employee getById(int id) {
		return dao.getEmployeeById(id);
	}
	
	@Transactional
	public Employee getByName(String fname, String lname) {
		return dao.getEmployeeByName(fname, lname);
	}

	@Transactional
	public Employee update(Employee employee) {
		return dao.updateEmployee(employee);
	}
}