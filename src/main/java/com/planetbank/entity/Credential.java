package com.planetbank.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "credentials")
public class Credential implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCredential;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;

	@OneToOne
	@JoinColumn(name = "id_client")
	@JsonIgnore
	private Client client;

	public Credential() {
	}

	public Credential(Integer idCredential, String username, String password, Client client) {
		this.idCredential = idCredential;
		this.username = username;
		this.password = password;
		this.client = client;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Integer getIdCredential() {
		return idCredential;
	}

	public void setIdCredential(Integer idCredential) {
		this.idCredential = idCredential;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
