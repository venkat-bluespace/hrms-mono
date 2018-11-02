package com.bluespace.tech.hrms.security.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bluespace.tech.hrms.domain.employee.EmployeeDetails;
import com.bluespace.tech.hrms.security.domain.UserAccount;

public interface AccountApprovalRepository extends MongoRepository<UserAccount, String> {

	/*public abstract AccountApproval findAccountApprovalByUserAccountId(Long paramLong);*/

//	public abstract UserAccount findAccountApprovalByIdPendingApproval(Long paramLong);

	public abstract UserAccount findAccountApprovalByEmployeeDetails(EmployeeDetails employeeDetails);

/*	public abstract List<AccountApproval> findAccountApprovalByUserAccountIdAndStatus(long paramLong,
			String paramString);*/

	public abstract List<UserAccount> findAll();

	public abstract UserAccount findAccountApprovalByUserName(String paramString);

}
