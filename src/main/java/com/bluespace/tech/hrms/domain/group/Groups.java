package com.bluespace.tech.hrms.domain.group;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bluespace.tech.hrms.domain.client.Client;
import com.bluespace.tech.hrms.domain.general.CurrentStatus;
import com.bluespace.tech.hrms.security.domain.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groups {

	@Id private int id;
	private String groupName;
	private String groupDesc;
	@DBRef private List<CurrentStatus> status;
	@DBRef private List<Client> client;
	private Date createdOn;
	@DBRef private UserAccount createdBy;
	@DBRef private List<UserAccount> modifiedBy;
	private Date modifiedOn;
	
}
