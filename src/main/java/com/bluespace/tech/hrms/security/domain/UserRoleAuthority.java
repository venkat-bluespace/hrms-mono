package com.bluespace.tech.hrms.security.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.bluespace.tech.hrms.security.constants.RoleConstants;

import lombok.EqualsAndHashCode;
import lombok.Data;


@Document
@Data
@EqualsAndHashCode(callSuper=false)
public class UserRoleAuthority extends BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private RoleConstants authorizedRole;
	private UserRole userRole;

}
