package com.planetbank.mapper;

import java.time.LocalDate;

import com.planetbank.entity.History;

public class HistoryMapper {

	private Integer history_id;
	private String account_number;
	private Double amount;
	private String movement_type;
	private LocalDate transaction_date;
	private Integer client_id;

	public HistoryMapper() {
	}

	public HistoryMapper(Integer history_id, String account_number, Double amount, String movement_type,
			LocalDate transaction_date, Integer client_id) {
		this.history_id = history_id;
		this.account_number = account_number;
		this.amount = amount;
		this.movement_type = movement_type;
		this.transaction_date = transaction_date;
		this.client_id = client_id;
	}

	public HistoryMapper(History history) {
		this.history_id = history.getRecord_id();
		this.account_number = history.getAccount().getAccountNumber();
		this.amount = history.getAmount();
		this.movement_type = history.getMovement_type();
		this.transaction_date = history.getTransaction_date();
		this.client_id = history.getClient_id();
	}

	public Integer getHistory_id() {
		return history_id;
	}

	public void setHistory_id(Integer history_id) {
		this.history_id = history_id;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getMovement_type() {
		return movement_type;
	}

	public void setMovement_type(String movement_type) {
		this.movement_type = movement_type;
	}

	public LocalDate getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(LocalDate transaction_date) {
		this.transaction_date = transaction_date;
	}

	public Integer getClient_id() {
		return client_id;
	}

	public void setClient_id(Integer client_id) {
		this.client_id = client_id;
	}

}
