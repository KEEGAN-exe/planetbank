package com.planetbank.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetbank.entity.Client;
import com.planetbank.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientRepository clientRepoitory;

	@Override
	public void insert(Client client) {
		clientRepoitory.save(client);
	}

	@Override
	public void update(Client client) {
		clientRepoitory.save(client);
	}

	@Override
	public void delete(Integer clientId) {
		clientRepoitory.deleteById(clientId);
	}

	@Override
	public Collection<Client> findAll() {
		return clientRepoitory.findAll();
	}

	@Override
	public Client findById(Integer clientId) {
		return clientRepoitory.findById(clientId).orElse(null);
	}
}
