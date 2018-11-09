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

@Document(collection = "displaypreferences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayPreferences {

	static public enum DefaultPage {
		DASHBOARD_WALL, MESSAGE_WALL, FRIENDS_WALL
	}

	@Id private int id;
	private int noOfRecordsToDisplay;
	@DBRef private List<Lead> lead;
	private DefaultPage defaultPageToDisplay;
	private Date dateCreated;
	private Date lastUpdatedDate;

}
