package com.bluespace.tech.hrms.domain.general;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bluespace.tech.hrms.domain.member.details.Lead;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "recommendations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recommendations {

	static public enum Status {
		APPROVED, DENIED, DELETED
	}

	@Id private int id;
	@DBRef private List<Lead> lead;
	@DBRef private List<Lead> recommendedLead;
	@DBRef private List<Status> status;
	private Date dateCreated;
	private Date recommendedDate;

}
