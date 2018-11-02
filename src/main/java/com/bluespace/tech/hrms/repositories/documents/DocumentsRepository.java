package com.bluespace.tech.hrms.repositories.documents;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bluespace.tech.hrms.domain.documents.Documents;

@Repository("documentsService")
public interface DocumentsRepository extends MongoRepository<Documents, Long> {

	public Documents findDocumentsByDocumentId(long documentId);
	
	public Documents findDocumentsByEmployee(long employeeId);
	
}