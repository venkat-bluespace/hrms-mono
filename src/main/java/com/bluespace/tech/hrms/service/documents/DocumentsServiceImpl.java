package com.bluespace.tech.hrms.service.documents;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bluespace.tech.hrms.domain.documents.Documents;
import com.bluespace.tech.hrms.dto.DocumentsDTO;
import com.bluespace.tech.hrms.repositories.documents.DocumentsRepository;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSUploadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;

public class DocumentsServiceImpl implements DocumentsService {

	private final static Logger logger = LoggerFactory.getLogger(DocumentsServiceImpl.class);
	
	@Autowired private DocumentsRepository documentsRepository;
	
	private Documents documentFiles;
	private MongoClient mongoClient;
	
	MongoDatabase db = mongoClient.getDatabase("hrms");
	GridFSBucket gridFSBucket = GridFSBuckets.create(db, "hrmsfiles");
	String gridBucket = gridFSBucket.getBucketName();
	GridFS gridFS = new GridFS((DB) db, gridBucket);

	@Override
	public Documents getAllDocumentsOfEmployee(long employeeId) {

		Bson filter = (Bson) this.documentsRepository.findDocumentsByEmployee(employeeId);
		gridFSBucket.find().filter(filter).forEach(new Block<GridFSFile>() {
			@Override
			public void apply(final GridFSFile gridFS) {
				gridFS.getFilename();
				logger.info("File : " + gridFS.getFilename());
			}
		});
		return documentFiles;

	}
	
	@Override
	public Documents getDocuments(long documentId) {
		logger.info("Calling DocumentsRepository to retrieve a documents based on documentId");
		documentFiles = documentsRepository.findDocumentsByDocumentId(documentId);
		return documentFiles;
	}

	@Override
	public void storeDocument(DocumentsDTO documents, long employeeId, String filePath, String fileName) {
		try {
			InputStream inputStream = new FileInputStream(new File(fileName));
			GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(25000000);
					//.metadata(new Document("type", "presentation"));
			ObjectId fileId = gridFSBucket.uploadFromStream(fileName, inputStream, options);
			logger.info("The fileId of the uploaded file is: " + fileId.toHexString());

/*			byte[] data = "Data to upload into GridFS".getBytes(StandardCharsets.UTF_8);
			GridFSUploadStream uploadStream = gridFSBucket.openUploadStream(filePath, options);
			uploadStream.write(data);
			uploadStream.close();*/
		} catch (FileNotFoundException e) {
			logger.error("The file was not found and failed with exception: " + e);
		}
		//return documentFiles;
		
	}
	
	@Override
	public Documents getDocument(String documentName) {
		Bson filter = Filters.eq("metadata.contentType", "pdf/txt/xlsx");
		gridFSBucket.find(filter).forEach(new Block<GridFSFile>() {
			@Override
			public void apply(GridFSFile gridFSFile) {
				String file = gridFSBucket.getBucketName();

				gridFSFile.getFilename();
				logger.error("Getting the documents for the file types of pdf/txt/xlsx " + gridFSFile.getFilename());
			}
		});
/*		GridFSDBFile gridFile = gridFS.findOne(gridBucket);
		InputStream inputStream = gridFile.getInputStream();

		BufferedReader buffer = null;
		try {
			String currentLine;
			buffer  = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		} catch (IOException e) {
			logger.error("File retrieval failed with exception: " + e);
		} finally {
			try {
				if(buffer != null)
					buffer.close();
			} catch (IOException ex) {
				logger.error("Failed with exception:" + ex);
			}
		}*/
		
		return null;
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
