package com.planetbank.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planetbank.entity.Client;
import com.planetbank.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService service;

	@GetMapping
	public ResponseEntity<?> getClients() {
		return new ResponseEntity<>(service.finAllClient(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> addClient(@RequestBody Client client) {
		client.setState(true);
		String generatedUser = service.generatedUser(client);
		String generatedTemporalPassword = service.generateTemporalPassword(client);
		service.insert(client);
		return new ResponseEntity<>("Registered successfully \nusername: " + generatedUser + "\nTemporal password: " + generatedTemporalPassword, HttpStatus.OK);
	}

	@PatchMapping("{clientId}")
	public ResponseEntity<?> editClient(@RequestBody Client newClient, @PathVariable Integer clientId) {

		Client oldClient = service.findById(clientId);
		if (oldClient != null) {
			if (newClient.getDni() != null) {
				oldClient.setDni(newClient.getDni());
			} else if (newClient.getAddress() != null) {
				oldClient.setAddress(newClient.getAddress());
			} else if (newClient.getName() != null) {
				oldClient.setName(newClient.getName());
			} else if (newClient.getLastname() != null) {
				oldClient.setLastname(newClient.getLastname());
			} else if (newClient.getAge() != null) {
				oldClient.setAge(newClient.getAge());
			} else if (newClient.getCountry() != null) {
				oldClient.setCountry(newClient.getCountry());
			} else if (newClient.getPhone() != null) {
				oldClient.setPhone(newClient.getPhone());
			} else if (newClient.getCity() != null) {
				oldClient.setCity(newClient.getCity());
			} else if (newClient.getBirthday() != null) {
				oldClient.setBirthday(newClient.getBirthday());
			} else if (newClient.getCredential() != null) {
				oldClient.setCredential(newClient.getCredential());
			}
			service.update(oldClient);
			return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Client updated error", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{dni}")
	public ResponseEntity<?> searchByDni(@PathVariable String dni) {
		Client client = service.findByDni(dni);
		if(client != null) {
			return new ResponseEntity<>(client,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search/country/{country}")
	public ResponseEntity<?> searchByCountry(@PathVariable String country){
		Collection<Client> client = service.findByCountry(country);
		if(client != null) {
			return new ResponseEntity<>(client,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search/city/{city}")
	public ResponseEntity<?> searchByCity(@PathVariable String city){
		Collection<Client> client = service.findByCity(city);
		if(client != null) {
			return new ResponseEntity<>(client,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search/underage/{age}")
	public ResponseEntity<?> searchByUnderAge(@PathVariable Integer age){
		Collection<Client> client = service.findByUnderAge(age);
		if(client != null) {
			return new ResponseEntity<>(client,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search/overage/{age}")
	public ResponseEntity<?> searchByOverAge(@PathVariable Integer age){
		Collection<Client> client = service.findByOverAge(age);
		if(client != null) {
			return new ResponseEntity<>(client,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{clientId}")
	public ResponseEntity<?> deleteClient(@PathVariable Integer clientId){
		Client client = service.findById(clientId);
		if(client != null) {
			client.setState(false);
			service.update(client);
			return new ResponseEntity<>("Deleted client", HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
