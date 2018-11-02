package com.bluespace.tech.hrms.domain.permissions;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bluespace.tech.hrms.domain.general.CurrentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="controls")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Control {
	
	static public enum Type {
		TEXT, 
		TEXT_AREA, 
		CHECK_BOX, 
		MULTI_SELECT
	}

	@Id private int id;
	private String controlKey;
	private String controlName;
	private CurrentStatus status;
	private Type type;
	private Date createdOn;
	private String createdBy;
	private Date modifedOn;
	private String modifiedBy;
	
}
