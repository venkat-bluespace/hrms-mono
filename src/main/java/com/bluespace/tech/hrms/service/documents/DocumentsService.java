package com.bluespace.tech.hrms.service.documents;

import com.bluespace.tech.hrms.domain.general.Documents;
import com.bluespace.tech.hrms.dto.DocumentsDTO;

public interface DocumentsService {

	public Documents getAllDocumentsOfEmployee(long employeeId);
	
	public Documents getDocuments(long paramId);
	
	public DocumentsDTO storeDocument(DocumentsDTO documents);
	
	public void updateDocument(long documentId);
	
	public boolean deleteDocument(long documentId);
	
}