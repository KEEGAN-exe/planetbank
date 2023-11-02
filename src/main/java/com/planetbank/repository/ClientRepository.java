package com.planetbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planetbank.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
