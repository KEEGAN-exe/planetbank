package com.planetbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planetbank.entity.Account;
import com.planetbank.entity.History;
import com.planetbank.entity.Movement;
import com.planetbank.service.AccountService;
import com.planetbank.service.HistoryService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService service;
	
	@Autowired
	private HistoryService history;

	@GetMapping
	public ResponseEntity<?> getAccounts() {
		return new ResponseEntity<>(service.findAllAccounts(), HttpStatus.OK);
	}

	@PatchMapping("{accountId}")
	public ResponseEntity<?> updateAccount(@RequestBody Account newAccount, @PathVariable Integer accountId) {
		Account oldAccount = service.findById(accountId);
		if (oldAccount != null) {
			if (newAccount.getAccountNumber() != null) {
				return new ResponseEntity<>("The account number cannot be updated", HttpStatus.UNAUTHORIZED);
			} else if (newAccount.getOpeningDate() != null) {
				return new ResponseEntity<>("The opening date cannot be updated", HttpStatus.UNAUTHORIZED);
			} else if (newAccount.getClient() != null) {
				return new ResponseEntity<>("The client cannot be updated", HttpStatus.UNAUTHORIZED);
			} else if (newAccount.getStatus() != null) {
				return new ResponseEntity<>("The status cannot be updated", HttpStatus.BAD_REQUEST);
			} else if (newAccount.getBalance() != null) {
				return new ResponseEntity<>("If you want to update the balance, make a deposit or withdrawa",
						HttpStatus.BAD_REQUEST);
			} else if (newAccount.getWithdrawalKey() != null) {
				if (newAccount.getWithdrawalKey().length() != 4) {
					return new ResponseEntity<>("The withdrawal key must be 4 digits", HttpStatus.BAD_REQUEST);
				}
				String withdrawalKey = newAccount.getWithdrawalKey();
				if (withdrawalKey.matches("\\d+")) {
					oldAccount.setWithdrawalKey(withdrawalKey);
					service.update(oldAccount);
					return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("WithdrawalKey must contain only numbers", HttpStatus.BAD_REQUEST);
				}
			}
		}
		return new ResponseEntity<>("Client error", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("{accountId}")
	public ResponseEntity<?> searchById(@PathVariable Integer accountId) {
		Account account = service.findByAccountId(accountId);
		if (account != null) {
			return new ResponseEntity<>(account, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/search/account_number/{accountNumber}")
	public ResponseEntity<?> searchByAccountNumber(@PathVariable String accountNumber) {
		Account account = service.findByAccountNumber(accountNumber);
		if (account != null) {
			return new ResponseEntity<>(account, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/deposit")
	public ResponseEntity<?> addMoney(@RequestBody Movement data) {
		try {
			Account account = service.findByAccountNumber(data.getAccount_number());
			if (account != null) {
				if (data.getAmount() > 0) {
					account.setBalance(account.getBalance() + data.getAmount());
					service.update(account);
					History record = new History(null, "deposit", data.getAmount(), account.getClient().getIdClient(), null, account, account.getAccountNumber());
					history.insert(record);
					return new ResponseEntity<>("Deposit completed successfully.", HttpStatus.OK);
				}
				return new ResponseEntity<>("Invalid amount", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>("Invalid account", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/withdraw")
	public ResponseEntity<?> withdrawMoney(@RequestBody Movement data) {
		try {
			Account account = service.findByAccountNumber(data.getAccount_number());
			if (account != null) {
				if (account.getWithdrawalKey() != null) {
					if (data.getAmount() <= 0 || data.getAmount() > account.getBalance()) {
						return new ResponseEntity<>("Invalid amount, your balance: $" + account.getBalance(),
								HttpStatus.BAD_REQUEST);
					}
					if (account.getWithdrawalKey().equals(data.getWithdrawalKey())) {
						account.setBalance(account.getBalance() - data.getAmount());
						service.update(account);
						History record = new History(null, "withdraw", data.getAmount(), account.getClient().getIdClient(), null, account, account.getAccountNumber());
						history.insert(record);
						return new ResponseEntity<>("Withdrawmoney completed successfully.", HttpStatus.OK);
					}

				} else {
					return new ResponseEntity<>("Change the withdrawal key to perform this operation",
							HttpStatus.FORBIDDEN);
				}
				if (!account.getWithdrawalKey().equals(data.getWithdrawalKey())) {
					return new ResponseEntity<>("The withdrawal key does not match the account number",
							HttpStatus.FORBIDDEN);
				}
			}
			return new ResponseEntity<>("Invalid account", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
