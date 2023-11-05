package com.planetbank.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.planetbank.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

	@Query(value = "SELECT * FROM clients WHERE dni = ? AND state = true", nativeQuery = true)
	public abstract Client findByDni(String dni);
	
	@Query(value = "SELECT * FROM clients WHERE country = ? AND state = true", nativeQuery = true)
	public abstract Collection<Client> findByCountry(String country);
	
	@Query(value = "SELECT * FROM clients WHERE city = ? AND state = true", nativeQuery = true)
	public abstract Collection<Client> findByCity(String country);
	
	@Query(value = "SELECT * FROM clients WHERE age < ? AND state = true", nativeQuery = true)
	public abstract Collection<Client> findByUnderAge(Integer age);
	
	@Query(value = "SELECT * FROM clients WHERE age > ? AND state = true", nativeQuery = true)
	public abstract Collection<Client> findByOverAge(Integer age);
}

