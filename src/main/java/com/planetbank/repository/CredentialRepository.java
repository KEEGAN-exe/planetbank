package com.planetbank.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.planetbank.entity.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Integer>{

	@Query(value = "SELECT * FROM credentials WHERE username LIKE ?%", nativeQuery = true)
	public abstract Collection<Credential> findByUsername(String username);
}
