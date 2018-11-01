package com.bluespace.tech.hrms.service.documents;

import com.bluespace.tech.hrms.domain.documents.Documents;
import com.bluespace.tech.hrms.dto.DocumentsDTO;

public interface DocumentsService {

	public Documents getAllDocumentsOfEmployee(long employeeId);
	
	public Documents getDocuments(long paramId);
	
	public void storeDocument(DocumentsDTO documents, long employeeId, String filePath, String fileName);
	
	public Documents getDocument(String paramString);
	
	public void updateDocument(long documentId);
	
	public boolean deleteDocument(long documentId);
	
}