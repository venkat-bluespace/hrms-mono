package com.bluespace.tech.hrms.service.client;

import java.util.List;

import com.bluespace.tech.hrms.domain.client.Client;
import com.bluespace.tech.hrms.security.domain.TokenVerification;

public abstract interface ClientService {

	public abstract void createTokenVerification(Client paramUserAccount, String paramString);

	public abstract TokenVerification getTokenVerification(String paramString);

	public abstract String getEncodedPassword(String paramString);

	/* CRUD Operations performed for Client */
	
	public abstract Client addClient(Client paramUserAccount);

	public abstract List<Client> getAllClients();
	
	public abstract Client getClientById(long clientId);
	
	public abstract boolean updateClient(Client clientDetails, long clientId);
	
	public abstract boolean deleteClient(long clientId);
	
}
