package com.bluespace.tech.hrms.controller.documents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bluespace.tech.hrms.dto.DocumentsDTO;
import com.bluespace.tech.hrms.service.documents.DocumentsService;

public class DocumentsController {

	private final static Logger logger = LoggerFactory.getLogger(DocumentsController.class);

	@Autowired
	private DocumentsService documentsService;

	@GetMapping(path = "/{employeeId}/documents/{documentId}", consumes = { MediaType.APPLICATION_PDF_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_PDF_VALUE,
					MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<DocumentsDTO> getAllDocuments(@PathVariable long employeeId, @PathVariable long documentId) {
		logger.info("Calling DocumentsService for retreiving a document with documentId");
		this.documentsService.getDocuments(documentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(path = "/{employeeId}/documents/{documentId}", consumes = { MediaType.APPLICATION_PDF_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_PDF_VALUE,
					MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<DocumentsDTO> getDocuments(@PathVariable long employeeId, @PathVariable long documentId) {
		logger.info("Calling DocumentsService for retreiving a document with documentId");
		this.documentsService.getDocuments(documentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// POST | This methods saves uploaded files into GridFS DB
	@PostMapping(path = "/{employeeId}/documents", consumes = { MediaType.APPLICATION_PDF_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_PDF_VALUE,
					MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<DocumentsDTO> storeDocuments(@RequestBody DocumentsDTO documentsDto,
			@PathVariable long employeeId, @PathVariable String filePath, String fileName) {
		logger.info("Calling DocumentsService for storing documents into the database");
		this.documentsService.storeDocument(documentsDto, employeeId, filePath, fileName);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path = "/{employeeId}/documents/{documentId}", consumes = { MediaType.APPLICATION_PDF_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_PDF_VALUE,
					MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<DocumentsDTO> updateDocuments(@Validated @RequestBody DocumentsDTO documents, @PathVariable long documentId, @PathVariable long employeeId) {
		logger.info("Calling DocumentsService for storing documents into the database");
		this.documentsService.updateDocument(documentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{employeeId}/documents/{documentId}", consumes = { MediaType.APPLICATION_PDF_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_PDF_VALUE,
					MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<DocumentsDTO> deleteDocuments(@PathVariable long employeeId, @PathVariable long documentId) {
		logger.info("Calling DocumentsService for retreiving a document with documentId");
		this.documentsService.getDocuments(documentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
