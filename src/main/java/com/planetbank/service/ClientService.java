package com.planetbank.service;

import java.util.Collection;

import com.planetbank.entity.Client;

public interface ClientService {

	public abstract void insert(Client client);
	public abstract void update(Client client);
	public abstract void delete(Integer clientId);
	public abstract Client findById(Integer clientId);
	public abstract Collection<Client> finAllClient();
	public abstract String generatedUser(Client client);
	public abstract String generateTemporalPassword(Client client);
	public abstract Client findByDni(String dni);
	public abstract Collection<Client> findByCountry(String country);
	public abstract Collection<Client> findByCity(String country);
	public abstract Collection<Client> findByUnderAge(Integer age);
	public abstract Collection<Client> findByOverAge(Integer age);
}
