package com.planetbank.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planetbank.entity.Account;
import com.planetbank.mapper.HistoryMapper;
import com.planetbank.service.AccountService;
import com.planetbank.service.HistoryService;
import com.planetbank.util.Mapper;

@RestController
@RequestMapping("/history")
public class HistoryController {

	@Autowired
	private HistoryService service;

	@Autowired
	private AccountService account;

	@GetMapping
	public ResponseEntity<?> getHistory() {
		return new ResponseEntity<>(Mapper.toHistory(service.findAll()), HttpStatus.OK);
	}

	@GetMapping("{account_number}")
	public ResponseEntity<?> findByAccount(@PathVariable String account_number) {
		Integer account_id = 0;
		Account data = account.findByAccountNumber(account_number);
		if (data != null) {
			account_id = data.getIdAccount();
			Collection<HistoryMapper> history = Mapper.toHistory(service.findByAccountNumber(account_id.toString()));
			if (!history.isEmpty()) {
				return new ResponseEntity<>(history, HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
