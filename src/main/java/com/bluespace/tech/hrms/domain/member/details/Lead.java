package com.bluespace.tech.hrms.domain.member.details;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bluespace.tech.hrms.domain.client.Client;
import com.bluespace.tech.hrms.domain.general.Person;
//import com.bluespace.tech.hrms.security.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="lead")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lead {

	enum Status {
		PASSIVE, TRAINING, INTERVIEW_SCHEDULE, OFFERED, ONBOARD, ACTIVE, DELETED
	}

	@Id private int id;
	private Person person;
	private String source;
	private String presentJobTitle;
	private String persentCompany;
	private String domain;
	private String communication_EmailAddress;
	private String userName;
	@DBRef private List<Client> client;
	private Date dateCreated;
	private Date lastUpdatedDate;
	private Status status;
	//@DBRef private List<User> marketingExecutive;
	
}