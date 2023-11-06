package com.planetbank.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
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
	private Boolean state;
	@Column(nullable = false)
	private String status;

	@OneToOne
	@JoinColumn(name = "id_client")
	private Client client;

	public Account() {
	}

	public Account(Integer idAccount, String accountNumber, Double balance, LocalDate openingDate, Boolean state,
			String status, Client client) {
		this.idAccount = idAccount;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.openingDate = openingDate;
		this.state = state;
		this.status = status;
		this.client = client;
	}

	@PrePersist
	public void PreDate() {
		this.openingDate = LocalDate.now();
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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

}
