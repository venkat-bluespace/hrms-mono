package com.bluespace.tech.hrms.security.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bluespace.tech.hrms.security.domain.AccountApproval;

public interface AccountApprovalRepository extends MongoRepository<AccountApproval, Long> {

	  public abstract AccountApproval findAccountApprovalBy_id(ObjectId _id);
	  
	  public abstract AccountApproval findAccountApprovalByIdPendingApproval(Long paramLong);
	  
	  public abstract List<AccountApproval> findAll();
	  
}
