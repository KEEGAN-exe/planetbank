package com.planetbank.service;

import java.util.Collection;

import com.planetbank.entity.History;

public interface HistoryService {

	public abstract void insert(History history);
	public abstract void update(History history);
	public abstract void delete(Integer historyId);
	public abstract History findById(Integer historyId);
	public abstract Collection<History> findByAccountNumber(String account_number);
	public abstract Collection<History> findAll();
}
