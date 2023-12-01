package com.planetbank.util;

import java.util.ArrayList;
import java.util.Collection;

import com.planetbank.entity.Client;
import com.planetbank.entity.History;
import com.planetbank.mapper.ClientMapper;
import com.planetbank.mapper.HistoryMapper;

public class Mapper {

	public static Collection<ClientMapper> toClient(Collection<Client> clients) {

		Collection<ClientMapper> collection = new ArrayList<>();
		for (Client client : clients) {
			ClientMapper mapper = new ClientMapper(client);
			collection.add(mapper);
		}

		return collection;

	}
	
	public static Collection<HistoryMapper> toHistory(Collection<History> records) {

		Collection<HistoryMapper> collection = new ArrayList<>();
		for (History history : records) {
			HistoryMapper mapper = new HistoryMapper(history);
			collection.add(mapper);
		}

		return collection;

	}
}
