package com.bluespace.tech.hrms.dto;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.bluespace.tech.hrms.domain.employee.EmployeeDetails;

import lombok.Data;

@Data
public class UserAccountDTO {

	private String userName;
	private String password;
	private boolean isActive;
	@DBRef private EmployeeDetails employeeDetails;
	private String userType;
	private Collection<String> roles;
	private Date createdOn;
	private String createdBy;
	private Date modifiedOn;
	private String modifiedBy;

}