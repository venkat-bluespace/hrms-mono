package com.bluespace.tech.hrms.security.domain;

import java.util.Collection;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bluespace.tech.hrms.domain.employee.EmployeeDetails;
import com.bluespace.tech.hrms.domain.general.Roles;

import lombok.Data;

@Document
@Data
public class UserAccount {

	@Id private ObjectId _id;
	private String userName;
	private String password;
	private boolean isActive;
	private String accountStatus;
	@DBRef private EmployeeDetails employeeDetails;
	private String userType;
	private Collection<Roles> roles;
	private Date createdOn;
	private String createdBy;
	private Date modifiedOn;
	private String modifiedBy;
	
}