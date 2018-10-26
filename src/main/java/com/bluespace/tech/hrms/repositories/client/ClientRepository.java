package com.bluespace.tech.hrms.repositories.client;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bluespace.tech.hrms.domain.client.Client;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

	public abstract Client findByEmailAddress(String paramString);

	public abstract Client getClientByClientId(long paramLong);
	
	public abstract Client findByClientName(String paramString);
	
	public abstract String deleteByClientId(long clientId);

}
