package com.bluespace.tech.hrms.controller.employee;

//import java.io.InputStream;
import java.io.IOException;

import java.text.ParseException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bluespace.tech.hrms.domain.client.Client;
import com.bluespace.tech.hrms.domain.employee.EmployeeDetails;
import com.bluespace.tech.hrms.dto.EmployeeDetailsDTO;
import com.bluespace.tech.hrms.exception.EntityNotFoundException;
import com.bluespace.tech.hrms.mappers.EmployeeDetailsMapper;
import com.bluespace.tech.hrms.service.employee.EmployeeService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mongodb.MongoException;

//import com.sun.jersey.multipart.FormDataParam;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
public class EmployeeController {

	private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	public String getClientDetails(String clientName) {

		try {
			Client client = new Client();
			clientName = client.getClientName();
		} catch (MongoException e) {
			logger.error("Client " + clientName + " does not exist in the system.");
		}
		return clientName;
	}

	// Get the list of all Employees of a Client
	@GetMapping(path = "/employee", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public List<EmployeeDetailsDTO> getAllEmployees() {
		List<EmployeeDetailsDTO> empDTOList = EmployeeDetailsMapper.mapEntitytoDTOList(employeeService.getAllEmployees());
		return empDTOList;
	}
	
	// Get Employee details based off his id
	@GetMapping(path = "/employee/{employeeId}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public EmployeeDetailsDTO getEmployeeById(@PathVariable("employeeId") long id) {
		EmployeeDetails employeeDetails = employeeService.getEmployeeById(id);
		EmployeeDetailsDTO employeeDetailsDto = EmployeeDetailsMapper.mapEntityToDTO(employeeDetails);
		return employeeDetailsDto;
	}

	// Update Employee details based on his employeeId
	@PutMapping(path = "/employee/{employeeId}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> updateEmployee(@Validated @RequestBody EmployeeDetailsDTO employeeDetails,
			@PathVariable("employeeId") long id) {
		if (this.employeeService.updateEmployee(employeeDetails, id))
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	// Creates or adds a new employee to a client
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(path = "/employee/create", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE }, produces = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE,
					MediaType.MULTIPART_FORM_DATA_VALUE })

	public EmployeeDetailsDTO addEmployee(@RequestParam("profileImage") MultipartFile multipartFile,
			HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException, ParseException {

		EmployeeDetailsDTO employeeDetails = EmployeeDetailsMapper.convertRequestToObject(request);
		employeeDetails.setProfileImage(EmployeeDetailsMapper.insert(multipartFile));
		EmployeeDetails newEmployeeDetails = employeeService.createNewEmployee(employeeDetails);
		EmployeeDetailsDTO employeeDetailsDto = EmployeeDetailsMapper.mapEntityToDTO(newEmployeeDetails);
		return employeeDetailsDto;
	}

	// Delete an employee based on his employeeId
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/employee/{employeeId}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Void> deleteEmployee(@PathVariable long employeeId) throws EntityNotFoundException {
		if (this.employeeService.deleteByEmployeeId(employeeId))
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
