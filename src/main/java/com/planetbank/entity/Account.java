package com.planetbank.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAccount;
	@Column(nullable = false)
	private String accountNumber;
	@Column(nullable = false)
	private Double balance;
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	private LocalDate openingDate;
	@Column(nullable = false)
	private Boolean status;

	public Account() {
	}

	public Account(Integer idAccount, String accountNumber, Double balance, LocalDate openingDate, Boolean status) {
		this.idAccount = idAccount;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.openingDate = openingDate;
		this.status = status;
	}

	public Integer getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
