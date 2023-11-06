package com.planetbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planetbank.entity.Account;
import com.planetbank.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService service;
	
	@GetMapping
	public ResponseEntity<?> getAccounts(){
		return new ResponseEntity<>(service.findAllAccounts(),HttpStatus.OK);
	}
	
	@GetMapping("{accountId}")
	public ResponseEntity<?> searchById(@PathVariable Integer accountId){
		Account account = service.findByAccountId(accountId);
		if(account != null) {
			return new ResponseEntity<>(account,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/search/account_number/{accountNumber}")
	public ResponseEntity<?> searchByAccountNumber(@PathVariable String accountNumber){
		Account account = service.findByAccountNumber(accountNumber);
		if(account != null) {
			return new ResponseEntity<>(account,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
