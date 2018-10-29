package com.bluespace.tech.hrms.domain.role;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bluespace.tech.hrms.domain.client.Client;
import com.bluespace.tech.hrms.domain.general.CurrentStatus;
import com.bluespace.tech.hrms.security.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

	private int id;
	private String roleName;
	private String roleDesc;
	@DBRef private List<Client> client;
	private CurrentStatus accountStatus;
	private Date createdOn;
	private User createdBy;
	private Date modifiedOn;
	private User modifiedBy;

}