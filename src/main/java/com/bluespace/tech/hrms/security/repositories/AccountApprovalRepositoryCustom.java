package com.bluespace.tech.hrms.security.repositories;

import java.util.List;

import com.bluespace.tech.hrms.dto.PendingAccountApprovalsDTO;

public interface AccountApprovalRepositoryCustom {
	
	public abstract List<PendingAccountApprovalsDTO> getPendingApprovals(String paramString);

}