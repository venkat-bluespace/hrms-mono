package com.bluespace.tech.hrms.domain.permissions;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bluespace.tech.hrms.domain.role.Role;
import com.bluespace.tech.hrms.security.domain.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {

	@Id private int id;
	private List<Role> role;
	private List<Control> control;
	private boolean value;
	private Date createdOn;
	private List<UserAccount> createdBy;
	private Date modifiedOn;
	private List<UserAccount> modifiedBy;
	
}