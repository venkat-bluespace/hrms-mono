package com.bluespace.tech.hrms.service.documents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bluespace.tech.hrms.domain.documents.Documents;
import com.bluespace.tech.hrms.exception.FileStorageException;
import com.bluespace.tech.hrms.repositories.documents.DocumentsRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSUploadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;

@Service
public class DocumentsServiceImpl implements DocumentsService {

	private final static Logger logger = LoggerFactory.getLogger(DocumentsServiceImpl.class);
	
	private final Path rootLocation = null;

	@Autowired
	private DocumentsRepository documentsRepository;

	private Documents documentFiles;
	
	@Autowired
	private MongoClient mongoClient;
	
	@Autowired
	private GridFsOperations gridFSOperations;

/*	MongoDatabase db = mongoClient.getDatabase("hrms");
	GridFSBucket gridFSBucket = GridFSBuckets.create(db, "hrmsfiles");*/

	@Override
	public Documents getAllDocumentsOfEmployee(long employeeId) {
		MongoDatabase db = mongoClient.getDatabase("hrms");
		GridFSBucket gridFSBucket = GridFSBuckets.create(db, "hrmsfiles");

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
	public String storeDocument(MultipartFile fileName, long employeeId) throws FileNotFoundException {
		MongoDatabase db = mongoClient.getDatabase("hrms");
		GridFSBucket gridFSBucket = GridFSBuckets.create(db, "hrmsfiles");

/*		try {
			String filePath = fileName.getBytes();
			String uploadedFile = StringUtils.cleanPath(fileName.getOriginalFilename());
			logger.info("Name of the file is: " + filePath);
			logger.info("Name of the file is: " + uploadedFile);
			if (filePath.contains("..")) {
				throw new FileStorageException(
						"Cannot store file with relative path outside current directory: " + fileName);
			}
			InputStream inputStream = new FileInputStream(new File(filePath));
			
			GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1024)
					.metadata(new Document("type", "pdf file"));
			ObjectId fileId = gridFSBucket.uploadFromStream(filePath, inputStream, options);

			DBObject metaData = new BasicDBObject();
			metaData.put("Immigration Documents", inputStream);
			logger.info("Metadata: " + metaData);
			gridFSOperations.store(new FileInputStream("inputStream"), metaData);

//			byte[] data = "Data to upload into GridFS ".getBytes(StandardCharsets.UTF_8);
			byte[] data = fileName.getBytes();
			GridFSUploadStream uploadStream = gridFSBucket.openUploadStream(filePath, options);
			uploadStream.write(data);
			uploadStream.close();
			logger.info("The fileId of the uploaded file is: " + fileId.toHexString());

		} catch (FileStorageException e) {
			logger.error("Cannot store the file as it failed with exception: " + e);
		} catch (FileNotFoundException fe) {
			logger.error("The file was not found and failed with exception: " + fe);
		}*/
		return "Done";
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
