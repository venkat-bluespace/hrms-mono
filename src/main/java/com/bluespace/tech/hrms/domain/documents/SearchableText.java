package com.bluespace.tech.hrms.domain.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "searchtext")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchableText {

	@Id private int id;
	private int leadAttachment_id;
	private String searchableText;
	private int lead_id;
	private String documentType;
}
