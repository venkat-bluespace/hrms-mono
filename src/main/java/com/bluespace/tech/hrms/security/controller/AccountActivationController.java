package com.bluespace.tech.hrms.security.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.CrossOrigin;

//import com.bluespace.tech.hrms.domain.client.Client;
//import com.bluespace.tech.hrms.domain.general.CurrentStatus;
import com.bluespace.tech.hrms.dto.PendingAccountApprovalsDTO;
import com.bluespace.tech.hrms.repositories.client.ClientRepository;
import com.bluespace.tech.hrms.security.core.OnRegistrationCompletionEvent;
//import com.bluespace.tech.hrms.security.domain.AccountApproval;
import com.bluespace.tech.hrms.security.domain.UserAccount;
import com.bluespace.tech.hrms.security.repositories.AccountApprovalRepository;
import com.bluespace.tech.hrms.security.repositories.AccountApprovalRepositoryCustom;
import com.bluespace.tech.hrms.security.repositories.TokenVerificationRepository;
import com.bluespace.tech.hrms.security.repositories.UserAccountRepository;
import com.bluespace.tech.hrms.security.util.MailTemplateConfiguration;

@RestController
@CrossOrigin
@RequestMapping({ "/accounts" })
public class AccountActivationController {
	@Autowired
	UserAccountRepository userAccountRepository;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	@Autowired
	MailTemplateConfiguration mailTemplateConfiguration;
	@Autowired
	AccountApprovalRepositoryCustom accountApprovalRepositoryCustom;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	AccountApprovalRepository accountApprovalRepository;
	@Autowired
	TokenVerificationRepository tokenVerificationRepository;
	private static final Logger logger = LoggerFactory.getLogger(ClientRegistrationController.class.getName());

	@PostMapping({ "/approveRequest" })
	public void verifyUserAccount(@RequestParam("user") String userName, @RequestParam("type") String type,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserAccount retrievedUser = null;
		try {
			UserAccount userAccount = this.userAccountRepository.findUserAccountByUserName(userName);
			if ("APPROVE".equalsIgnoreCase(type)) {
				userAccount.setAccountStatus("APPROVED");
			} else if ("REJECT".equalsIgnoreCase(type)) {
				userAccount.setAccountStatus("REJECTED");
			} /*else if ("HOLD".equalsIgnoreCase(type)) {
				repo.setStatus("ON HOLD");
			}*/
			userAccount = this.accountApprovalRepository.save(userAccount);
/*			retrievedUser = this.userAccountRepository.findUserAccountByUserName(userAccount.getIdPendingApproval().toString());
			if ("APPROVE".equalsIgnoreCase(type)) {
				retrievedUser.setVerifiedByAdmin(true);
				String companyName = retrievedUser.getCompanyRegistration().getCompanyName();

				Client company = this.clientRepository.findByClientName(companyName);
				company.setApproved(true);
				this.clientRepository.save(company);
				logger.info("Approved Company Registration and updated records in DB successfully");
				retrievedUser = (UserAccount) this.userAccountRepository.save(retrievedUser);
			}*/
			URL url = new URL(request.getRequestURL().toString());
			String host = url.getHost();
			String scheme = url.getProtocol();
			int port = url.getPort();
			URI uri = new URI(scheme, null, host, port, null, null, null);
			logger.info("Captured Server Url : " + uri.toString());

			OnRegistrationCompletionEvent event = new OnRegistrationCompletionEvent(retrievedUser, request.getLocale(),
					uri.toString(), false, type);
			logger.debug("Event : " + event);
			this.eventPublisher.publishEvent(event);
			response.setStatus(200);
			response.getOutputStream().println("{ \"success\": \" Verification email sent \"}");
		} catch (Exception me) {
			logger.error(
					"verification link sending failed: " + me.getMessage() + ", Rolling back previous transaction");
			me.printStackTrace();
			this.tokenVerificationRepository.delete(this.tokenVerificationRepository.findTokenByUserAccount(retrievedUser));
			response.setStatus(400);
			response.getOutputStream().println("{ \"error\": \" Failed to send verification email \"}");
		}
	}

	@PostMapping(value = { "/getPendingApprovals" }, produces = { "application/json" })
	public List<PendingAccountApprovalsDTO> getPendingApprovals(@RequestParam("userName") String userName) {
		List<PendingAccountApprovalsDTO> pendingApprovalList = this.accountApprovalRepositoryCustom
				.getPendingApprovals(userName);
		logger.info("response : " + pendingApprovalList);
		return pendingApprovalList;
	}
}
