package com.bluespace.tech.hrms.domain.general;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bluespace.tech.hrms.domain.member.details.Lead;
import com.bluespace.tech.hrms.security.domain.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="communicationlogentry")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationLogEntry {

	@Id private int id;
	private String message;
	private Date dateSent;
	private Date dateViewed;
	@DBRef private List<UserAccount> sentBy;
	@DBRef private List<Lead> lead;
	private String link;
	private Date dateCreated;
	private Date lastUpdatedDate;

}
