package com.planetbank.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetbank.entity.History;
import com.planetbank.repository.HistoryRepository;

@Service
public class HistoryServiceImpl implements HistoryService{
	
	@Autowired
	private HistoryRepository repository;

	@Override
	public void insert(History history) {
		repository.save(history);
	}

	@Override
	public void update(History history) {
		repository.save(history);
		
	}

	@Override
	public void delete(Integer historyId) {
		repository.deleteById(historyId);
	}

	@Override
	public History findById(Integer historyId) {
		return repository.findById(historyId).orElse(null);
	}

	@Override
	public Collection<History> findByAccountNumber(String account_number) {
		return repository.findByAccountNumber(account_number);
	}

	@Override
	public Collection<History> findAll() {
		return repository.findAll();
	}

}
