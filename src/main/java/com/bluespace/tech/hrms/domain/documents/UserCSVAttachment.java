package com.bluespace.tech.hrms.domain.documents;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bluespace.tech.hrms.domain.client.Client;
import com.bluespace.tech.hrms.security.domain.UserAccount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "usercsvattachment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCSVAttachment {

	static public enum Status {
		NOTSTARTED, 
		INPROGRESS, 
		COMPLETED, 
		DELETED, 
		MAPPINGINCOMPLETE, 
		COMPLETEDWITHDUPLICATES, 
		ERROR_PROCESSING_CSV
	}

    @Id private int id;
    private String name;
    private String description;
    private byte[] fileAttachment;
    private long size;
    private Date uploadedDate;
    private String source;
    private String sendSMSNotification;
    private String excludeFromMassCommunication;
    @DBRef private List<Client> client;
    private String mappingParams;
    @DBRef private List<Status> currentStatus;
    @DBRef private List<UserAccount> user;
    private String action;
    private String errorMessage;
    private boolean hasHeaderRow;
    private int numberOfRows;
}
