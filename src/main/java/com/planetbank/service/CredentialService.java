package com.planetbank.service;

import java.util.Collection;

import com.planetbank.entity.Credential;

public interface CredentialService {

	public abstract void insert(Credential credential);
	public abstract void update(Credential credential);
	public abstract void delete(Integer credentialId);
	public abstract Credential findById(Integer credentialId);
	public abstract Collection<Credential> findAll();
	public abstract Collection<Credential> findByUsername(String username);
}
