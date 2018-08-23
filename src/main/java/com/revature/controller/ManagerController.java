package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Manager;
import com.revature.service.ManagerService;

@RestController
public class ManagerController {
	@Autowired
	private ManagerService service;
	
	public void setService(ManagerService service) {
		this.service = service;
	}
	
	@GetMapping("/manager/{id}")
	public Manager byId(@PathVariable("id") long id) {
		return service.getById(id);
	}
	
	@GetMapping("/test")
	public String test() {
		return service.getById(1).toString();
	}
}
