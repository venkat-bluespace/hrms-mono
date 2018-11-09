package com.bluespace.tech.hrms.domain.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "leadattachment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeadAttachment {

	@Id private int id;
	private int leadId;
	private String filename;
	private byte[] filedata;
	private double fileSize;
	private String contentType;
	private String documentType;
	private String timestampString;
	private boolean processed = false;

	public String toString() {
		return "LeadAttachment{" + "id=" + id + ", leadId=" + leadId + ", filename='" + filename + '\'' + ", fileSize="
				+ fileSize + ", contentType='" + contentType + '\'' + ", timestampString='" + timestampString + '\''
				+ ", processed='" + processed + '\'' + '}';
	}
}
