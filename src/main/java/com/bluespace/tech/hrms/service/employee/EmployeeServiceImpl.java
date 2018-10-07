package com.bluespace.tech.hrms.service.employee;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bluespace.tech.hrms.config.MongoConfig;
import com.bluespace.tech.hrms.domain.employee.EmployeeDetails;
import com.bluespace.tech.hrms.repositories.employee.EmployeeRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

/*	@Autowired
	private MongoConfig mongoConfig;*/

	private MongoClient mongoClient = null;

	public EmployeeDetails createNewEmployee(@ModelAttribute EmployeeDetails newEmployee) {
		try {
			/*
			 * MongoCollection<Document> collection =
			 * mongoConfig.db().getCollection("employeedetails"); BasicDBObject document =
			 * new BasicDBObject(); document.put(key, v)
			 */

			newEmployee.set_id(ObjectId.get());
			return employeeRepository.save(newEmployee);

		} catch (MongoException e) {
			logger.error("Connection failed due to " + e);
		} finally {
			mongoClient.close();
		}
		return newEmployee;
	}

	@Override
	public EmployeeDetails getEmployeeById(long employeeId) {
		return employeeRepository.getEmployeeByEmployeeId(employeeId);
	}

	public List<EmployeeDetails> getEmployeesList() {
		return employeeRepository.findAll();
	}

}
