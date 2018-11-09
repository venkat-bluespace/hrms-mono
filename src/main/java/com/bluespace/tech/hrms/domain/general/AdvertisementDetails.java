package com.bluespace.tech.hrms.domain.general;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bluespace.tech.hrms.domain.member.details.Lead;

import lombok.Data;

@Data
@Document(collection = "advertisementdetails")
public class AdvertisementDetails {

	static public enum Visiblity {
		PUBLIC, PRIVATE
	}

	static public enum Type {
		VIDEO, AUDIO, WEBURL
	}

	@Id private int id;
	private Lead lead;
	private String url;
	private Visiblity visibility;
	@DBRef private List<CurrentStatus> status;
	private Date dateCreated;
	private Date lastUpdatedDate;
	private Type addType;
}
