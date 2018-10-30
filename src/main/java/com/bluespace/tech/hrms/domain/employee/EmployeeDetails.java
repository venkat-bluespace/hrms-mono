package com.bluespace.tech.hrms.domain.employee;

//import java.util.Date;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
//import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//import com.fasterxml.jackson.annotation.JsonFormat;

//import com.bluespace.tech.hrms.domain.client.Client;

import lombok.Data;

@Document
@Data
@CompoundIndexes({ @CompoundIndex(name = "employeeId_index", def = "{'_id': 1 , 'employeeId': 1}", unique = true) })
public class EmployeeDetails {

	@Id
	private ObjectId _id;

	private long employeeId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String legalName;
	private String emailId;
	private String primaryEmail;
	private String secondaryEmail;
	private String mobileNumber;
	private String homePhoneNumber;
	private String workPhoneNumber;
	private String ssn;
/*	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date dateOfBirth;*/
	private String dateOfBirth;
	private String gender;
	private Binary profileImage;
	private String primaryContact;
	private String primaryContactRelation;
	private String primaryContactPhone;
	private String primaryContactAltPhone;
	private String secondaryContact;
	private String secondaryContactRelation;
	private String secondaryContactPhone;
	private String secondaryContactAltPhone;
/*	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date hireDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date terminationDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date employmentLastDate;*/

	private String hireDate;
	private String terminationDate;
	private String employmentLastDate;
	
	//@DBRef
	private String clientName;

	//@DBRef
	private String currentStatus;

	private String jobTitle;
	private String organisation;
	private String department;
	private double empSalary;
	private String employmentType;
	private String employmentStatus;
	private String reportingManager;
	private boolean active;
/*	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date createdOn;*/
	private String createdOn;
	private String createdBy;
/*	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
	private Date modifiedOn;*/
	private String modifiedOn;
	private String modifiedBy;

	public EmployeeDetails(long employeeId, boolean active, String currentStatus) {
		super();
		this.employeeId = employeeId;
		this.active = active;
		this.currentStatus = currentStatus;
	}

	public EmployeeDetails() {

	}

}