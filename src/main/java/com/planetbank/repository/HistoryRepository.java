package com.planetbank.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.planetbank.entity.History;

public interface HistoryRepository extends JpaRepository<History, Integer>{

	
	@Query(value = "SELECT * FROM records WHERE account_id = ?", nativeQuery = true)
	public abstract Collection<History> findByAccountNumber(String account_number);
}
