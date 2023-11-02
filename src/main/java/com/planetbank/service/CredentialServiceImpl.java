package com.planetbank.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetbank.entity.Credential;
import com.planetbank.repository.CredentialRepository;

@Service
public class CredentialServiceImpl implements CredentialService{

	@Autowired
	private CredentialRepository repository;

	@Override
	public void insert(Credential credential) {
		repository.save(credential);
	}

	@Override
	public void update(Credential credential) {
		repository.save(credential);
	}

	@Override
	public void delete(Integer credentialId) {
		repository.deleteById(credentialId);
	}

	@Override
	public Credential findById(Integer credentialId) {
		return repository.findById(credentialId).orElse(null);
	}

	@Override
	public Collection<Credential> findAll() {
		return repository.findAll();
	}
}
