package com.bluespace.tech.hrms.domain.documents;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import com.bluespace.tech.hrms.domain.client.Client;
import com.bluespace.tech.hrms.domain.employee.EmployeeDetails;

import lombok.Data;

@Document
@Data
public class Documents {
	@Id private ObjectId _id;
	private long documentId;
	private MultipartFile fileName;
	@DBRef private EmployeeDetails employee;
	@DBRef private Client client;
	private Double fileSize;
	private String filePath;
	private Date createdOn;
	private String createdBy;
	private Date modifiedOn;
	private String modifiedBy;
	
	public Documents(MultipartFile fileName, Double fileSize, String filePath) {
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.filePath = filePath;
	}
}
