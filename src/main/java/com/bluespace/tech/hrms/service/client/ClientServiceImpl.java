package com.bluespace.tech.hrms.service.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bluespace.tech.hrms.domain.client.Client;
import com.bluespace.tech.hrms.exception.EntityNotFoundException;
import com.bluespace.tech.hrms.repositories.client.ClientRepository;
import com.bluespace.tech.hrms.security.domain.TokenVerification;
import com.bluespace.tech.hrms.security.repositories.TokenVerificationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

@Service
@Component
public class ClientServiceImpl implements ClientService {

	private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);
	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private MongoClient mongoClient;
	
	//@Autowired
	private TokenVerificationRepository tokenVerificationRepository;

	@Override
	public void createTokenVerification(Client client, String token) {
		TokenVerification newToken = new TokenVerification(token, client);
		this.tokenVerificationRepository.save(newToken);
	}
	
	@Override
	public TokenVerification getTokenVerification(String token) {
		return this.tokenVerificationRepository.findByToken(token);
	}
	
	@Override
	public String getEncodedPassword(String rawPassword) {
		return this.passwordEncoder.encode(rawPassword);
	}

	/* CRUD Operations performed for Client */
	
	// Add a new Client to the system
	@Override
	public Client addClient(Client client) {
		return this.clientRepository.save(client);
	}

	// Gets the list of all the Clients existing in the system
	@Override
	public List<Client> getAllClients() {
		return this.clientRepository.findAll();
	}

	// Gets the Client details based on {clientId}
	@Override
	public Client getClientById(long clientId) {
		Client clientDetails = clientRepository.getClientByClientId(clientId);
		if (clientDetails == null) {
			throw new EntityNotFoundException(
					"There is no record of employee in the system with the id: " + clientId);
		}
		return clientRepository.getClientByClientId(clientId);
	}
	
	@Override
	public boolean updateClient(Client clientDetails, long clientId) {

		/*mongoClient = new MongoClient("localhost", 27017);*/
		MongoDatabase db = mongoClient.getDatabase("hrms");
		MongoCollection<Document> collection = db.getCollection("client");
		ObjectMapper mapper = new ObjectMapper();
		Client id = this.clientRepository.getClientByClientId(clientId);

		try {
			if (id == null) {
				logger.error("The client " + clientDetails.getClientName() + "does not exist in the system.");
				throw new EntityNotFoundException("There is no Client information existing with the provided id.");
			}

			Bson filter = eq("clientId", clientId);
			@SuppressWarnings("unchecked")
			Map<String, Object> requestMap = mapper.convertValue(clientDetails, Map.class);
			List<Bson> bsonDoc = new ArrayList<Bson>();

			for (String key : requestMap.keySet()) {
				Object value = requestMap.get(key);
				if (value != null && value != (Integer) 0 && value != (Double) 0.0) {
					bsonDoc.add(set(key, value));
				}
			}

			Calendar currentTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			bsonDoc.add(set("clientId", clientId));
			bsonDoc.add(set("modifiedOn", currentTime.getTime()));
			Bson document = combine(bsonDoc);

			UpdateResult result = collection.updateOne(filter, document);

			if (result.getModifiedCount() > 0) {
				return true;
			}
		} finally {
			mongoClient.close();
		}
		return false;
	}

	// Deletes the Client details based on {clientId}
	@Override
	public boolean deleteClient(long clientId) {
		this.clientRepository.deleteByClientId(clientId);
		return false;
	}

}
