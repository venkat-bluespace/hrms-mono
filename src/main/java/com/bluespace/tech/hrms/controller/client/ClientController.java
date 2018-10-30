package com.bluespace.tech.hrms.controller.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bluespace.tech.hrms.domain.client.Client;
import com.bluespace.tech.hrms.exception.UserNotFoundException;
import com.bluespace.tech.hrms.service.client.ClientService;

@Component
@RestController
@RequestMapping("/")
public class ClientController {

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	private ClientService clientService;

	// GET | Retrieve list of all Clients
	@GetMapping(path = "/client", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public List<Client> getAllClients() {
		return this.clientService.getAllClients();
	}

	// GET | Retrieve details of a client based on {clientId}
	@GetMapping(path = "/client/{clientId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Client getClientInfo(long clientId) {
		return this.clientService.getClientById(clientId);
	}

	// POST | Add New Client
	@PostMapping(path = "/client/add", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Client addNewClient(@Validated @RequestBody Client clientDetails) {
		clientDetails = this.clientService.addClient(clientDetails);
		return clientDetails;
	}

	// PUT | Update Client Info in the system based on the provided {clientId}
	@PutMapping(path = "/client/{clientId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> updateClient(@Validated @RequestBody Client clientDetails, @PathVariable long clientId) {
		Client client = this.clientService.getClientById(clientId);
		if(client == null) {
			logger.error("There is no record of the client with clientId: " + clientId + " existing in the system");
			throw new UserNotFoundException("The Client does not exist with the provided clientId");
		} else {
			this.clientService.updateClient(clientDetails, clientId);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	// DELETE | Delete or Make a Client Inactive in the system
	@DeleteMapping(path = "/client/{clientId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public String deleteClient(@RequestParam("clientId") long clientId) {
		this.clientService.deleteClient(clientId);
		return "redirect:/clients";
	}

}