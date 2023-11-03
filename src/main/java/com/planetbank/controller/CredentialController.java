package com.planetbank.controller;

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
	public ResponseEntity<?> updateCredential(@PathVariable Integer credentialId,@RequestBody Credential newCredential) {
		Credential oldCredential = service.findById(credentialId);
		if (oldCredential != null) {
			if(newCredential.getPassword() != null) {
				oldCredential.setPassword(newCredential.getPassword());
			}else if (newCredential.getUsername() != null) {
				return new ResponseEntity<>("The username cannot be updated.",HttpStatus.FORBIDDEN);
			}else if (newCredential.getClient() != null) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			
			service.update(oldCredential);
			return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Client updated error", HttpStatus.BAD_REQUEST);
	}
	
}
