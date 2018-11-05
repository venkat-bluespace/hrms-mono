package com.bluespace.tech.hrms.service.documents;

import java.io.FileNotFoundException;

import org.springframework.web.multipart.MultipartFile;

import com.bluespace.tech.hrms.domain.documents.Documents;

public interface DocumentsService {

	public Documents getAllDocumentsOfEmployee(long employeeId);

	public Documents getDocuments(long paramId);

	public String storeDocument(MultipartFile file, long employeeId) throws FileNotFoundException;

	public void updateDocument(long documentId);

	public boolean deleteDocument(long documentId);

}