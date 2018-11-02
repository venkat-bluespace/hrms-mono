package com.bluespace.tech.hrms.dto;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.bluespace.tech.hrms.domain.client.Client;
import com.bluespace.tech.hrms.domain.employee.EmployeeDetails;

import lombok.Data;

@Data
public class DocumentsDTO {
	@Id private ObjectId _id;
	private long documentId;
	private String documentName;
	@DBRef private EmployeeDetails employee;
	@DBRef private Client client;
	private Double fileSize;
	private String filePath;
	private Date createdOn;
	private String createdBy;
	private Date modifiedOn;
	private String modifiedBy;
}
