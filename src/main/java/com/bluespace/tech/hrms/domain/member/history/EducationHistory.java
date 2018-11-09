package com.bluespace.tech.hrms.domain.member.history;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bluespace.tech.hrms.domain.general.Address;
import com.bluespace.tech.hrms.domain.general.CurrentStatus;
import com.bluespace.tech.hrms.domain.member.details.Lead;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="educationhistory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationHistory {

	@Id private int id;
	private List<Address> address;
	private List<Lead> lead;
	private String schoolName;
	private String universityName;
	private DegreeType degreeType;
	private String specilization;
	private int startYear;
	private int endYear;
	private Date dateCreated;
	private Date lastUpdatedDate;
	private CurrentStatus status;
	
}
