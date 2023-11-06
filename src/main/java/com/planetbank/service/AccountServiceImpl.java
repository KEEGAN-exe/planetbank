package com.planetbank.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetbank.entity.Account;
import com.planetbank.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository repository;

	@Override
	public void insert(Account account) {
		repository.save(account);
	}

	@Override
	public void update(Account account) {
		repository.save(account);
	}

	@Override
	public void delete(Integer accountId) {
		repository.deleteById(accountId);
	}

	@Override
	public Account findById(Integer accountId) {
		return repository.findById(accountId).orElse(null);
	}

	@Override
	public Account findByAccountNumber(String accountNumber) {
		return repository.findByAccountNumber(accountNumber);
	}

	@Override
	public Collection<Account> findAllAccounts() {
		return repository.findAllAccounts();
	}

	@Override
	public Account findByAccountId(Integer accountId) {
		return repository.findByAccountId(accountId);
	}

}
