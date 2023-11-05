package com.planetbank.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planetbank.entity.Credential;
import com.planetbank.service.CredentialService;

@RestController
@RequestMapping("/credential")
public class CredentialController {

	@Autowired
	private CredentialService service;

	@GetMapping
	public ResponseEntity<?> getCredentials() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@PatchMapping("{credentialId}")
	public ResponseEntity<?> updateCredential(@PathVariable Integer credentialId,
			@RequestBody Credential newCredential) {
		Credential oldCredential = service.findById(credentialId);
		if (oldCredential != null) {
			if (newCredential.getPassword() != null) {
				oldCredential.setPassword(newCredential.getPassword());
			} else if (newCredential.getUsername() != null) {
				return new ResponseEntity<>("The username cannot be updated.", HttpStatus.FORBIDDEN);
			} else if (newCredential.getClient() != null) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}

			service.update(oldCredential);
			return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
		}

		return new ResponseEntity<>("Client updated error", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{userId}")
	public ResponseEntity<?> searchById(@PathVariable Integer userId){
		Credential credential = service.findById(userId);
		if (credential != null) {
			return new ResponseEntity<>(credential, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/search/{username}")
	public ResponseEntity<?> searchByUsername(@PathVariable String username) {
		Collection<Credential> credential = service.findByUsername(username);
		if (!credential.isEmpty()) {
			return new ResponseEntity<>(credential, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
