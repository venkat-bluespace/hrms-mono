package com.bluespace.tech.hrms.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bluespace.tech.hrms.security.domain.UserAccount;

@Repository
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

	public abstract UserAccount findUserAccountByUserName(String paramString);

}
