package com.bluespace.tech.hrms.domain.member.details;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bluespace.tech.hrms.domain.general.CurrentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="message")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

	static enum Type {
		 PRIVATE,PUBLIC
	}
	
	@Id private int id;
	private String message;
	private Date dateCreated;
	private Type  messageType;
	private Lead lead;
	private List<CurrentStatus> messageStatus;
	private Date lastUpdatedDate;
	
}
