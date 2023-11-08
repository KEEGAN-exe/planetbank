package com.planetbank.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		Collection<Credential> credentials = service.findAll();
		Collection<Credential> result = new ArrayList<>();
		for (Credential credential : credentials) {
			if (credential.getClient().getState() == true) {
				result.add(credential);
			}
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PatchMapping("{credentialId}")
	public ResponseEntity<?> updateCredential(@PathVariable Integer credentialId,
			@RequestBody Credential newCredential) {
		Credential oldCredential = service.findById(credentialId);
		if (oldCredential != null) {
			if (newCredential.getUsername() != null) {
				return new ResponseEntity<>("The username cannot be updated.", HttpStatus.FORBIDDEN);
			} else if (newCredential.getClient() != null) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			} else if (newCredential.getPassword() != null && passwordValidator(newCredential.getPassword())) {
				oldCredential.setPassword(newCredential.getPassword());
			} else {
				return new ResponseEntity<>("The password must have at least 2 number, 1 uppercase letter, 1 special character , and be a minimum of 5 characters.", HttpStatus.BAD_REQUEST);
			}

			service.update(oldCredential);
			return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
		}

		return new ResponseEntity<>("Client updated error", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("{userId}")
	public ResponseEntity<?> searchById(@PathVariable Integer userId) {
		Credential credential = service.findById(userId);
		if (credential != null) {
			return new ResponseEntity<>(credential, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search/{username}")
	public ResponseEntity<?> searchByUsername(@PathVariable String username) {
		Collection<Credential> credential = service.findByUsername(username);
		if (!credential.isEmpty()) {
			return new ResponseEntity<>(credential, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public static boolean passwordValidator(String password) {
		String regex = "^(?=.*[0-9].*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}
}
