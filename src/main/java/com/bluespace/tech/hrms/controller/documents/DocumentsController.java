package com.bluespace.tech.hrms.controller.documents;

import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bluespace.tech.hrms.dto.DocumentsDTO;
import com.bluespace.tech.hrms.exception.FileStorageException;
import com.bluespace.tech.hrms.service.documents.DocumentsService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class DocumentsController {

	private final static Logger logger = LoggerFactory.getLogger(DocumentsController.class);

	@Autowired
	private DocumentsService documentsService;

	@GetMapping(path = "/{employeeId}/documents", consumes = { MediaType.APPLICATION_PDF_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_PDF_VALUE,
					MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<DocumentsDTO> getAllDocuments(@PathVariable long employeeId) {
		logger.info("Calling DocumentsService for retreiving a document with documentId");
		this.documentsService.getAllDocumentsOfEmployee(employeeId);
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

	@PostMapping(path = "/{employeeId}/documents", consumes = { MediaType.APPLICATION_PDF_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_PDF_VALUE,
					MediaType.MULTIPART_FORM_DATA_VALUE })
	public String storeDocuments(@RequestParam("fileName") MultipartFile file, @PathVariable long employeeId,
			RedirectAttributes redirectAttributes) throws FileStorageException, FileNotFoundException {

		try {
			logger.info("Calling DocumentsService for storing documents into the database");
			this.documentsService.storeDocument(file, employeeId);
			redirectAttributes.addFlashAttribute("message",
					" The file " + file.getOriginalFilename() + " was uploaded successfully.");
		} catch (FileStorageException e) {
			logger.error("Failed to upload file with exception: " + e);
		}
		return "redirect:/";
	}

	@PutMapping(path = "/{employeeId}/documents/{documentId}", consumes = { MediaType.APPLICATION_PDF_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_PDF_VALUE,
					MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<DocumentsDTO> updateDocuments(@Validated @RequestBody DocumentsDTO documents,
			@PathVariable long documentId, @PathVariable long employeeId) {
		logger.info("Calling DocumentsService for storing documents into the database");
		this.documentsService.updateDocument(documentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping(path = "/{employeeId}/documents/{documentId}", consumes = { MediaType.APPLICATION_PDF_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_PDF_VALUE,
					MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<DocumentsDTO> deleteDocuments(@PathVariable long employeeId, @PathVariable long documentId) {
		logger.info("Calling DocumentsService for retreiving a document with documentId");
		this.documentsService.deleteDocument(documentId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
