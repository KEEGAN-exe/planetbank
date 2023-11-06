package com.planetbank.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.planetbank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	
	@Query(value = "SELECT * FROM accounts WHERE account_number = ? AND state = true", nativeQuery = true)
	public abstract Account findByAccountNumber(String accountNumber);
	
	@Query(value = "SELECT * FROM accounts WHERE state = true", nativeQuery = true)
	public abstract Collection<Account> findAllAccounts();
	
	@Query(value = "SELECT * FROM accounts WHERE id_account = ? AND state = true", nativeQuery = true)
	public abstract Account findByAccountId(Integer accountId);
}
