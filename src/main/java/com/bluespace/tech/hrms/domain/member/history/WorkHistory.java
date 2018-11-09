package com.bluespace.tech.hrms.domain.member.history;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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

@Document(collection="workhistory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkHistory {

	@Id private int id;
	private Date startDate;
	private Date endDate;
	private String company;
	private String jobTitle;
	private List<Address> address;
	private String jobDuties;
	private List<Lead> lead;
	private Boolean currentEmployer=false;
	private Date dateCreated;
	private Date lastUpdatedDate;
	private List<CurrentStatus> status;

	private DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
	
	public void setStartDate(int month, int year) {
		try {
			if (month > 0 && month < 13 && year > 1900 && year < (LocalDateTime.now().getYear())) {
				startDate = (Date) dateFormatter.parse("01/" + month + "" + year);
			}
		} catch (Exception exec) {
		}
	}
	
	public void setEndDate(int month, int year) {
		try {
			if (month > 0 && month < 13 && year > 1900 && year < (LocalDateTime.now().getYear())) {
				endDate = (Date) dateFormatter.parse("01/" + month + "" + year);
			}
		} catch (Exception exec) {
		}
	}
	
}
