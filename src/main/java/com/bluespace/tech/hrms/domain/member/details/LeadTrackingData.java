package com.bluespace.tech.hrms.domain.member.details;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="leadtrackingdata")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadTrackingData {

	@Id private int id;
	private String source;
	private String visitorId;
	private String referrer;
	private Date createdDate;
	private List<Lead> lead;
	private String jobId;
	private List<LeadPreferences> preferences;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		LeadTrackingData that = (LeadTrackingData) o;

		if (!lead.equals(that.lead))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = lead.hashCode();
		return result;
	}
	
}
