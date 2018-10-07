package com.bluespace.tech.hrms.repositories.client;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bluespace.tech.hrms.domain.client.Client;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

	public abstract Client findClientByEmailAddress(String paramString);

	public abstract Client findClientByClientId(long paramLong);

	public abstract Client findClientByClientName(String paramString);
	
	public abstract String deleteClientByClientName(String clientName);

}
