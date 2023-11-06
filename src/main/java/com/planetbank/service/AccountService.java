package com.planetbank.service;

import java.util.Collection;

import com.planetbank.entity.Account;

public interface AccountService {

	public abstract void insert(Account account);
	public abstract void update(Account account);
	public abstract void delete(Integer accountId);
	public abstract Account findById(Integer accountId);
	public abstract Collection<Account> findAllAccounts();
	public abstract Account findByAccountId(Integer accountId);
	public abstract Account findByAccountNumber(String accountNumber);
}
