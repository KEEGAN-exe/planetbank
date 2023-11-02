package com.planetbank.service;

import java.util.Collection;

import com.planetbank.entity.Client;

public interface ClientService {

	public abstract void insert(Client client);
	public abstract void update(Client client);
	public abstract void delete(Integer clientId);
	public abstract Client findById(Integer clientId);
	public abstract Collection<Client> findAll();
	public abstract String generatedUser(Client client);
	public abstract String generateTemporalPassword(Client client);
}
