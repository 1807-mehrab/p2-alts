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

import com.revature.beans.Client;
import com.revature.service.ClientService;

@RestController
public class ClientController {
	@Autowired
	private ClientService service;

	public void setService(ClientService service) {
		this.service = service;
	}

	@GetMapping("/client/{id}")
	public Client byId(@PathVariable("id") long id) {
		return service.getById(id);
	}

	@GetMapping(path = "/client/{username}/{password}")
	public @ResponseBody ResponseEntity<Client> byUsername(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		Client client = service.getByUsernameAndPassword(username, password);
		if (client == null)
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	@GetMapping("/client")
	public List<Client> clients() {
		return service.getAll();
	}

	@PostMapping(path = "/client", consumes = "application/json", produces = "application/json")
	public Client addClient(@RequestBody Client client) {
		service.add(client);
		return service.getByName(client.getFname(), client.getLname());
	}

	@DeleteMapping("/client/{id}")
	public @ResponseBody ResponseEntity<Client> deleteClient(@PathVariable("id") long id) {
		Client client = service.getById(id);
		if (client == null) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}

		service.delete(client);
		return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(path = "/client/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Client> updateUser(@PathVariable("id") long id, @RequestBody Client client) {
		Client currentClient = service.getById(id);

		if (currentClient == null) {
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
		}

		ClientController.populate(currentClient, client);

		service.update(currentClient);
		return new ResponseEntity<Client>(currentClient, HttpStatus.OK);
	}

	protected static void populate(Client currentClient, Client client) {
		currentClient.setFname(client.getFname());
		currentClient.setLname(client.getLname());
		currentClient.setEmail(client.getEmail());
		currentClient.setPassword(client.getPassword());
		currentClient.setUsername(client.getUsername());
	}
}
