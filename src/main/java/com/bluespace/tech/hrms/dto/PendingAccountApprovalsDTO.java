package com.bluespace.tech.hrms.dto;

import lombok.Data;

@Data
public class PendingAccountApprovalsDTO {

	private long serialNo;
	private long id;
	private String name;
	private String email;
	private String companyName;
	private String registrationRequestDate;
	private String address;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private boolean approved;
	private boolean onHold;
	private boolean rejected;
	private String status;

}