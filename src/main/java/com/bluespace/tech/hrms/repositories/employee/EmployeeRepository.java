package com.bluespace.tech.hrms.repositories.employee;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bluespace.tech.hrms.domain.employee.EmployeeDetails;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeDetails, String> {

	public abstract EmployeeDetails findEmployeeByPrimaryEmail(String paramString);

	public abstract EmployeeDetails getEmployeeByEmployeeId(long employeeId);
}