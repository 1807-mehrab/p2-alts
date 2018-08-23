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

	@GetMapping("/manager")
	public List<Manager> managers() {
		return service.getAll();
	}

	@PostMapping(path = "/manager", consumes = "application/json", produces = "application/json")
	public Manager addManager(@RequestBody Manager manager) {
		service.add(manager);
		return service.getByName(manager.getFname(), manager.getLname());
	}

	@DeleteMapping("/manager/{id}")
	public @ResponseBody ResponseEntity<Manager> deleteManager(@PathVariable("id") long id) {
		Manager manager = service.getById(id);
		if (manager == null) {
			return new ResponseEntity<Manager>(HttpStatus.NOT_FOUND);
		}

		service.delete(manager);
		return new ResponseEntity<Manager>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(path = "/manager/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Manager> updateUser(@PathVariable("id") long id, @RequestBody Manager manager) {
        System.out.println("Updating User " + id);
         
        Manager currentManager = service.getById(id);
         
        if (currentManager==null) {
            return new ResponseEntity<Manager>(HttpStatus.NOT_FOUND);
        }
 
        ManagerController.populate(currentManager, manager);
        
        service.update(currentManager);
        return new ResponseEntity<Manager>(currentManager, HttpStatus.OK);
    }
	
	protected static void populate(Manager currentManager, Manager manager) {
		currentManager.setFname(manager.getFname());
        currentManager.setLname(manager.getLname());
        currentManager.setEmail(manager.getEmail());
        currentManager.setPassword(manager.getPassword());
        currentManager.setUsername(manager.getUsername());
	}
}
