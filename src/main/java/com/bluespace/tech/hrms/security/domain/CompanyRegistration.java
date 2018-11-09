package com.bluespace.tech.hrms.security.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CompanyRegistration extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String companyName;
	private String description;
	private boolean approved = false;
	
	public String toString() {
		return "CompanyRegistration [companyName=" + this.companyName + ", description=" + this.description + ", id="
				+ getId() + "]";
	}

}