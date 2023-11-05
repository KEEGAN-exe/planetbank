package com.planetbank.service;

import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetbank.entity.Client;
import com.planetbank.entity.Credential;
import com.planetbank.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepoitory;
	
	@Autowired
	private CredentialService credentialService;
	
	String generatedUsername;
	String generatedPassword;
	
	@Override
	public void insert(Client client) {
		clientRepoitory.save(client);
		Credential credential = new Credential(null,generatedUsername,generatedPassword,client);
		credentialService.insert(credential);
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

	@Override
	public String generatedUser(Client client) {
		Integer nameSize = (int) Math.ceil((double) client.getName().length() / 2);
		Integer lastNameSize = (int) Math.ceil((double) client.getLastname().length() / 2);
		String username = client.getName().substring(0, nameSize)
				+ client.getLastname().substring(client.getLastname().length() - lastNameSize)
				+ client.getDni().substring(0, 3);
		generatedUsername = username;
		return username;
	}

	@Override
	public String generateTemporalPassword(Client client) {
		String name = client.getName();
		String lastName = client.getLastname();
		String dni = client.getDni();
		String dniPart = dni.substring(0, 3);
		String namePart = name.substring(0, 2).toLowerCase();
		String lastNamePart = lastName.substring(0, 2).toLowerCase();
		String randomPart = generateRandomString(3);

		String password = dniPart + namePart + lastNamePart + randomPart;

		generatedPassword = password;
		return password;
	}
	
	public String generateRandomString(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder randomString = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			randomString.append(characters.charAt(random.nextInt(characters.length())));
		}

		return randomString.toString();
	}

	@Override
	public Client findByDni(String dni) {
		return clientRepoitory.findByDni(dni);
	}

	@Override
	public Collection<Client> findByCountry(String country) {
		return clientRepoitory.findByCountry(country);
	}

	@Override
	public Collection<Client> findByCity(String city) {
		return clientRepoitory.findByCity(city);
	}

	@Override
	public Collection<Client> findByUnderAge(Integer age) {
		return clientRepoitory.findByUnderAge(age);
	}

	@Override
	public Collection<Client> findByOverAge(Integer age) {
		return clientRepoitory.findByOverAge(age);
	}
}
