package com.planetbank.controller;

import java.time.LocalDate;
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
		Collection<Client> client = service.finAllClient();
		if (!client.isEmpty()) {
			return new ResponseEntity<>(client, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping
	public ResponseEntity<?> addClient(@RequestBody Client client) {
		Client findClient = service.findByFirstDni(client.getDni());
		LocalDate eightyYearsAgo = LocalDate.now().minusYears(80);
		LocalDate age = LocalDate.now().minusYears(client.getBirthday().getYear());
		try {
			if (client.getDni().length() != 8) {
				return new ResponseEntity<>("Invalid document", HttpStatus.BAD_REQUEST);
			} else if (findClient != null && !findClient.getState()) {
				return new ResponseEntity<>(
						"This document '" + client.getDni() + "' already exists but is deactivated.",
						HttpStatus.BAD_REQUEST);
			} else if (findClient != null && findClient.getState()) {
				return new ResponseEntity<>("This document '" + client.getDni() + "' already exists.",
						HttpStatus.BAD_REQUEST);
			} else if (client.getPhone().length() < 9 || client.getPhone().length() > 9) {
				return new ResponseEntity<>("Invalid phone number", HttpStatus.BAD_REQUEST);
			} else if (client.getBirthday().isBefore(eightyYearsAgo)) {
				return new ResponseEntity<>("Invalid birthday", HttpStatus.BAD_REQUEST);
			} else if (client.getBirthday().isAfter(LocalDate.now())) {
				return new ResponseEntity<>("Invalid birthday", HttpStatus.BAD_REQUEST);
			} else if (age.getYear() < 18) {
				return new ResponseEntity<>("You must be an adult", HttpStatus.FORBIDDEN);
			}

			client.setState(true);
			client.setAge(age.getYear());
			String generatedUser = service.generatedUser(client);
			String generatedTemporalPassword = service.generateTemporalPassword(client);
			String generatedAccountNumber = service.generateAccountNumber();
			service.insert(client);
			return new ResponseEntity<>("Registered successfully \nusername: " + generatedUser + "\nTemporal password: "
					+ generatedTemporalPassword + "\nAccountNumber: " + generatedAccountNumber, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Client registration error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PatchMapping("{clientId}")
	public ResponseEntity<?> editClient(@RequestBody Client newClient, @PathVariable Integer clientId) {
		try {
			if (newClient.getDni() != null && newClient.getDni().length() != 8) {
				return new ResponseEntity<>("Invalid document.", HttpStatus.BAD_REQUEST);
			} else if (newClient.getBirthday() != null
					&& newClient.getBirthday().isBefore(LocalDate.now().minusYears(80))) {
				return new ResponseEntity<>("Invalid birthday.", HttpStatus.BAD_REQUEST);
			} else if (newClient.getBirthday() != null
					&& newClient.getBirthday().isAfter(LocalDate.now().minusYears(18))) {
				return new ResponseEntity<>("Invalid birthday.", HttpStatus.BAD_REQUEST);
			}

			Client oldClient = service.findById(clientId);
			if (oldClient != null) {
				if (newClient.getDni() != null && newClient.getDni().length() == 8) {
					oldClient.setDni(newClient.getDni());
				} else if (newClient.getAddress() != null) {
					oldClient.setAddress(newClient.getAddress());
				} else if (newClient.getName() != null) {
					oldClient.setName(newClient.getName());
				} else if (newClient.getLastname() != null) {
					oldClient.setLastname(newClient.getLastname());
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
			}
			return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Client updated error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{clientId}")
	public ResponseEntity<?> searchById(@PathVariable Integer clientId) {
		try {
			Client client = service.findById(clientId);
			if (client != null) {
				return new ResponseEntity<>(client, HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Client search error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search/dni/{dni}")
	public ResponseEntity<?> searchByDni(@PathVariable String dni) {
		try {
			if (dni.length() < 8 || dni.length() > 8) {
				return new ResponseEntity<>("Invalid document", HttpStatus.BAD_REQUEST);
			}
			Client client = service.findByDni(dni);
			if (client != null) {
				return new ResponseEntity<>(client, HttpStatus.OK);
			}

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Client search error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search/country/{country}")
	public ResponseEntity<?> searchByCountry(@PathVariable String country) {
		try {
			if (country.length() == 0) {
				return new ResponseEntity<>("Invalid country", HttpStatus.BAD_REQUEST);
			}
			Collection<Client> client = service.findByCountry(country);
			if (!client.isEmpty()) {
				return new ResponseEntity<>(client, HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Client search error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search/city/{city}")
	public ResponseEntity<?> searchByCity(@PathVariable String city) {
		try {
			if (city.length() == 0) {
				return new ResponseEntity<>("Invalid country", HttpStatus.BAD_REQUEST);
			}
			Collection<Client> client = service.findByCity(city);
			if (!client.isEmpty()) {
				return new ResponseEntity<>(client, HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Client search error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search/underage/{age}")
	public ResponseEntity<?> searchByUnderAge(@PathVariable Integer age) {
		try {
			if (age <= 0 || age > 80) {
				return new ResponseEntity<>("Invalid age range.", HttpStatus.BAD_REQUEST);
			}
			Collection<Client> client = service.findByUnderAge(age);
			if (!client.isEmpty()) {
				return new ResponseEntity<>(client, HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Client search error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search/overage/{age}")
	public ResponseEntity<?> searchByOverAge(@PathVariable Integer age) {
		try {
			if (age < 0 || age > 80) {
				return new ResponseEntity<>("Invalid age range.", HttpStatus.BAD_REQUEST);
			}
			Collection<Client> client = service.findByOverAge(age);
			if (!client.isEmpty()) {
				return new ResponseEntity<>(client, HttpStatus.OK);
			}

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>("Client search error.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("{clientId}")
	public ResponseEntity<?> deleteClient(@PathVariable Integer clientId) {
		Client client = service.findById(clientId);
		if (client != null) {
			client.setState(false);
			service.update(client);
			return new ResponseEntity<>("Deleted client", HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
