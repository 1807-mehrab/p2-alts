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

import com.revature.beans.Request;
import com.revature.service.RequestService;

@RestController
public class RequestController {
	@Autowired
	private RequestService service;

	public void setService(RequestService service) {
		this.service = service;
	}

	@GetMapping("/request/{id}")
	public Request byId(@PathVariable("id") long id) {
		return service.getById(id);
	}

	@GetMapping("/request")
	public List<Request> requests() {
		return service.getAll();
	}

	@PostMapping(path = "/request", consumes = "application/json", produces = "application/json")
	public Request addRequest(@RequestBody Request request) {
		service.add(request);
		return service.getByTitleAndClient(request.getTitle(), request.getClient());
	}

	@DeleteMapping("/request/{id}")
	public @ResponseBody ResponseEntity<Request> deleteRequest(@PathVariable("id") long id) {
		Request request = service.getById(id);
		if (request == null) {
			return new ResponseEntity<Request>(HttpStatus.NOT_FOUND);
		}

		service.delete(request);
		return new ResponseEntity<Request>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "/request/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Request> updateUser(@PathVariable("id") long id, @RequestBody Request request) {
		Request currentRequest = service.getById(id);

		if (currentRequest == null) {
			return new ResponseEntity<Request>(HttpStatus.NOT_FOUND);
		}

		RequestController.populate(currentRequest, request);

		service.update(currentRequest);
		return new ResponseEntity<Request>(currentRequest, HttpStatus.OK);
	}

	protected static void populate(Request currentRequest, Request request) {
		ClientController.populate(currentRequest.getClient(), request.getClient());
		ManagerController.populate(currentRequest.getManager(), request.getManager());
		currentRequest.setClient(request.getClient());
		currentRequest.setManager(request.getManager());

		currentRequest.setIsapproved(request.isIsapproved());
		currentRequest.setPhoto(request.getPhoto());
		currentRequest.setPrice(request.getPrice());
		currentRequest.setTitle(request.getTitle());
	}
}