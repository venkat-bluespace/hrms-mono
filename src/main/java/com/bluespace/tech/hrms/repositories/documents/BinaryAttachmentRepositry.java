package com.bluespace.tech.hrms.repositories.documents;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bluespace.tech.hrms.domain.documents.BinaryAttachment;

public interface BinaryAttachmentRepositry extends MongoRepository<BinaryAttachment, Long> {

}
