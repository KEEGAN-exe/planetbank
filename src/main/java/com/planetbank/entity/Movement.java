package com.planetbank.entity;

public class Movement {

	private String account_number;
	private Double amount;
	private String withdrawalKey;

	public Movement() {
	}

	public Movement(String account_number, Double amount, String withdrawalKey) {
		this.account_number = account_number;
		this.amount = amount;
		this.withdrawalKey = withdrawalKey;
	}

	public String getWithdrawalKey() {
		return withdrawalKey;
	}

	public void setWithdrawalKey(String withdrawalKey) {
		this.withdrawalKey = withdrawalKey;
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

}
