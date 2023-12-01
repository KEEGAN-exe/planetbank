package com.planetbank.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "records")
public class History implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer record_id;
	@Column(nullable = false)
	private String movement_type;
	@Column(nullable = false)
	private Double amount;
	@Column(nullable = false)
	private Integer client_id;
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd", iso = ISO.DATE)
	private LocalDate transaction_date;
	@ManyToOne
	@JoinColumn(name = "account_id", nullable = false)
	private Account account;

	public History() {
	}

	public History(Integer record_id, String movement_type, Double amount, Integer client_id,
			LocalDate transaction_date, Account account) {
		this.record_id = record_id;
		this.movement_type = movement_type;
		this.amount = amount;
		this.client_id = client_id;
		this.transaction_date = transaction_date;
		this.account = account;
	}

	@PrePersist
	public void prePersist() {
		transaction_date = LocalDate.now();
	}

	public Integer getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}

	public LocalDate getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(LocalDate transaction_date) {
		this.transaction_date = transaction_date;
	}

	public String getMovement_type() {
		return movement_type;
	}

	public void setMovement_type(String movement_type) {
		this.movement_type = movement_type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
