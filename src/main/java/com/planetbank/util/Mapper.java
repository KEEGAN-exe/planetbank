package com.planetbank.util;

import java.util.ArrayList;
import java.util.Collection;

import com.planetbank.entity.Client;
import com.planetbank.mapper.ClientMapper;

public class Mapper {

	public static Collection<ClientMapper> toClient(Collection<Client> clients) {

		Collection<ClientMapper> collection = new ArrayList<>();
		for (Client client : clients) {
			ClientMapper mapper = new ClientMapper(client);
			collection.add(mapper);
		}

		return collection;

	}
}
