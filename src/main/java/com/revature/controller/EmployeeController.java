package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Employee;
import com.revature.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService service;

	public void setService(EmployeeService service) {
		this.service = service;
	}

	@GetMapping("/employee/{id}")
	public Employee byId(@PathVariable("id") long id) {
		return service.getById(id);
	}

	@GetMapping("/employee")
	public List<Employee> employees() {
		return service.getAll();
	}

	@PostMapping(path = "/employee", consumes = "application/json", produces = "application/json")
	public Employee addEmployee(@RequestBody Employee employee) {
		service.add(employee);
		return service.getByName(employee.getFname(), employee.getLname());
	}

	@DeleteMapping("/employee/{id}")
	public @ResponseBody ResponseEntity<Employee> deleteEmployee(@PathVariable("id") long id) {
		Employee employee = service.getById(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}

		service.delete(employee);
		return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "/employee/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Employee> updateUser(@PathVariable("id") long id, @RequestBody Employee employee) {
		Employee currentEmployee = service.getById(id);

		if (currentEmployee == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}

		EmployeeController.populate(currentEmployee, employee);

		service.update(currentEmployee);
		return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
	}

	protected static void populate(Employee currentEmployee, Employee employee) {
		currentEmployee.setFname(employee.getFname());
		currentEmployee.setLname(employee.getLname());
	}
}