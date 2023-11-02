package com.planetbank.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_types")
public class AccountType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idType;
	@Column(nullable = false)
	private String AccountTypeName;

	public AccountType() {
	}

	public AccountType(Integer idType, String accountTypeName) {
		this.idType = idType;
		AccountTypeName = accountTypeName;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getAccountTypeName() {
		return AccountTypeName;
	}

	public void setAccountTypeName(String accountTypeName) {
		AccountTypeName = accountTypeName;
	}

}
