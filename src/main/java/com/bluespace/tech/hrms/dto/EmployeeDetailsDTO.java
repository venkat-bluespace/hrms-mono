package com.bluespace.tech.hrms.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class EmployeeDetailsDTO {

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
	private String dateOfBirth;
	private String gender;
	private MultipartFile profileImage;
	private String primaryContact;
	private String primaryContactRelation;
	private String primaryContactPhone;
	private String primaryContactAltPhone;
	private String secondaryContact;
	private String secondaryContactRelation;
	private String secondaryContactPhone;
	private String secondaryContactAltPhone;
	private String hireDate;
	private String terminationDate;
	private String employmentLastDate;
	private String clientName;
	private String currentStatus;
	private String jobTitle;
	private String organisation;
	private String department;
	private double empSalary;
	private String employmentType;
	private String employmentStatus;
	private String reportingManager;
	private boolean active;
	private String createdOn;
	private String createdBy;
	private String modifiedOn;
	private String modifiedBy;

}
