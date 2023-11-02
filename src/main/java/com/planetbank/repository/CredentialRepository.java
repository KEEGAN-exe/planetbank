package com.planetbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planetbank.entity.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Integer>{

}
