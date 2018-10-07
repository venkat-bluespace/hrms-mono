package com.bluespace.tech.hrms.security.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bluespace.tech.hrms.security.domain.UserRole;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRole, String> {
	
	/*public abstract UserRole findUserRoleByUserName(String paramString);*/

/*	public abstract List<UserRole> deleteByUserName(String userName);*/

/*	public abstract UserRole findOne(String userName);

	public abstract List<UserRole> findAll(List<String> userName);*/

}
