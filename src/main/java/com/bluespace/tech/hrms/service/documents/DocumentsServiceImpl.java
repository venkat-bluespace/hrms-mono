package com.bluespace.tech.hrms.service.documents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

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
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSUploadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

@Service
public class DocumentsServiceImpl implements DocumentsService {

	private final static Logger logger = LoggerFactory.getLogger(DocumentsServiceImpl.class);

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
	public void storeDocument(MultipartFile uploadedFile, long employeeId) {
		MongoDatabase db = mongoClient.getDatabase("hrms");
		GridFSBucket gridFSBucket = GridFSBuckets.create(db, "hrmsfiles");
		
/*		GridFS gridFS = new GridFS((DB) db, gridFSBucket.toString());
		logger.info("The GridFSBucket is created with the details: " + gridFSBucket);
		
		MongoCollection<Document> collection = db.getCollection("documents");*/
		
		String fileName = uploadedFile.getOriginalFilename();
		
/*		Documents documents = null;*/
		
		try {
			File file = new File(uploadedFile.getOriginalFilename());
			logger.info("Name of the uploaded file is: " + file);
			uploadedFile.transferTo(file);
		    GridFSUploadOptions options = new GridFSUploadOptions()
		                       .chunkSizeBytes(358400)
		                       .metadata(new Document("type", "ImmigrationFile"));

		    GridFSUploadStream uploadStream = gridFSBucket.openUploadStream(fileName, options);
		    byte[] data = Files.readAllBytes(new File(file.toString()).toPath());
		    uploadStream.write(data);
		    uploadStream.close();
		    System.out.println("The fileId of the uploaded file is: " + uploadStream.getObjectId().toHexString());
		    
/*		    GridFSInputFile gridfsFile = gridFS.createFile(file);
		    gridfsFile.setFilename(fileName);
		    gridfsFile.save();*/
		    
		    BasicDBObject info = new BasicDBObject();
		    info.put("fileName", documentFiles.getFileName());
		    info.put("uploadedBy", documentFiles.getEmployee().getEmployeeId());
		    info.put("filePath", documentFiles.getFilePath());

		} catch(IOException e){
			logger.error("Cannot upload the file due to the exception: " + e);
		}
	}

/*	@Override
	public String storeDocument(MultipartFile uploadedFile, long employeeId) throws FileNotFoundException {
		MongoDatabase db = mongoClient.getDatabase("hrms");
		GridFSBucket gridFSBucket = GridFSBuckets.create(db, "hrmsfiles");
		GridFSInputFile gridfsInputFile;

		try {
			GridFS gridfs = new GridFS((DB) db, gridFSBucket.toString());
			String uploadedFileName = uploadedFile.getOriginalFilename();
			InputStream inputStream = new FileInputStream(new File(uploadedFileName));
			gridfs.createFile(inputStream);
			
//			String uploadedFile = StringUtils.cleanPath(fileName.getOriginalFilename());
			logger.info("Name of the file is: " + uploadedFileName);
//			logger.info("Name of the file is: " + uploadedFile);
			if (uploadedFileName.contains("..")) {
				throw new FileStorageException(
						"Cannot store file with relative path outside current directory: " + uploadedFile);
			}
			
			GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(1024)
					.metadata(new Document("type", "file"));
//			ObjectId fileId = gridFSBucket.uploadFromStream(filePath, inputStream, options);

			DBObject metaData = new BasicDBObject();
			metaData.put("Immigration Documents", inputStream);
			logger.info("Metadata: " + metaData);
			gridFSOperations.store(new FileInputStream("inputStream"), metaData);

			byte[] data = uploadedFile.getBytes();
			GridFSUploadStream uploadStream = gridFSBucket.openUploadStream(uploadedFileName, options);
			uploadStream.write(data);
			uploadStream.close();


		} catch (FileStorageException e) {
			logger.error("Cannot store the file as it failed with exception: " + e);
		} catch (FileNotFoundException fe) {
			logger.error("The file was not found and failed with exception: " + fe);
		}  catch (IOException io) {
			logger.error("The file was not found and failed with exception: " + io);
		}
		return "Done";
	}*/

	@Override
	public void updateDocument(long documentId) {

	}

	@Override
	public boolean deleteDocument(long documentId) {
		// TODO Auto-generated method stub
		return false;
	}

}
