package com.bluespace.tech.hrms.service.documents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bluespace.tech.hrms.domain.general.Documents;
import com.bluespace.tech.hrms.dto.DocumentsDTO;
import com.bluespace.tech.hrms.repositories.documents.DocumentsRepository;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSUploadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;

public class DocumentsServiceImpl implements DocumentsService {

	private final static Logger logger = LoggerFactory.getLogger(DocumentsServiceImpl.class);
	
	@Autowired private DocumentsRepository documentsRepository;
	
	private Documents documentFiles;
	private MongoClient mongoClient;
	
	MongoDatabase db = mongoClient.getDatabase("hrms");
	GridFSBucket gridFSBucket = GridFSBuckets.create(db, "hrmsfiles");

	@Override
	public Documents getAllDocumentsOfEmployee(long employeeId) {

		gridFSBucket.find().forEach(new Block<GridFSFile>() {

			@Override
			public void apply(GridFSFile gridFS) {
				logger.info("File : " + gridFS.getFilename());
			}
		});

		return documentFiles;
	}
	
	@Override
	public Documents getDocuments(long documentId) {
		
		logger.info("Calling DocumentsRepository to retrieve a documents based on documentId");
		documentsRepository.findDocumentsByDocumentId(documentId);
		return documentFiles;
	}

	@Override
	public DocumentsDTO storeDocument(DocumentsDTO documents) {
		try {
			String filePath = documents.getFilePath();
			InputStream inputStream = new FileInputStream(new File(documents.getFilePath()));
			GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1024)
					.metadata(new Document("type", "presentation"));
			ObjectId fileId = gridFSBucket.uploadFromStream(filePath, inputStream, options);
			
			byte[] data = "Data to upload into GridFS".getBytes(StandardCharsets.UTF_8);
			GridFSUploadStream uploadStream = gridFSBucket.openUploadStream(filePath, options);
			uploadStream.write(data);
			uploadStream.close();
			logger.info("The fileId of the uploaded file is: " + fileId.toHexString());
		} catch (FileNotFoundException e) {
			logger.error("The file was not found and failed with exception: " + e);
		}
		return documents;
	}

	@Override
	public void updateDocument(long documentId) {

	}

	@Override
	public boolean deleteDocument(long documentId) {
		// TODO Auto-generated method stub
		return false;
	}

}
