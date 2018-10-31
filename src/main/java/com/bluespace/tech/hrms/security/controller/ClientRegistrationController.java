package com.bluespace.tech.hrms.security.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.bluespace.tech.hrms.domain.client.Client;
import com.bluespace.tech.hrms.domain.employee.EmployeeDetails;
import com.bluespace.tech.hrms.domain.general.Address;
import com.bluespace.tech.hrms.domain.general.Roles;
import com.bluespace.tech.hrms.repositories.client.ClientRepository;
import com.bluespace.tech.hrms.repositories.general.AddressRepository;
import com.bluespace.tech.hrms.security.domain.AccountApproval;
import com.bluespace.tech.hrms.security.domain.AccountCreationEmail;
import com.bluespace.tech.hrms.security.domain.DefaultResponse;
import com.bluespace.tech.hrms.security.domain.TokenVerification;
import com.bluespace.tech.hrms.security.domain.UserAccount;
//import com.bluespace.tech.hrms.security.repositories.AccountApprovalRepository;
import com.bluespace.tech.hrms.security.repositories.UserAccountRepository;
import com.bluespace.tech.hrms.security.service.UserAccountService;
import com.bluespace.tech.hrms.security.util.EmailHandler;
import com.bluespace.tech.hrms.security.util.MailTemplateConfiguration;
import com.bluespace.tech.hrms.service.client.ClientService;

@RestController
@RequestMapping("/")
public class ClientRegistrationController {

	private static Logger logger = LoggerFactory.getLogger(ClientRegistrationController.class);

	@Autowired
	private EmailHandler emailHandler;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	private UserAccountRepository userAccountRepository;

/*	@Autowired
	private AccountApprovalRepository accountApprovalRepository;*/

	@Autowired
	private MailTemplateConfiguration mailTemplateConfiguration;

	@GetMapping(value = "/welcome")
	public ResponseEntity<DefaultResponse> welcomeNewRegistration() {
		return new ResponseEntity<>(getWelcomeResponse(), HttpStatus.OK);
	}

	private DefaultResponse getWelcomeResponse() {
		DefaultResponse response = new DefaultResponse();
		response.setResponseCode(200L);
		response.setResponseMessage("New Registration Controller");
		return response;
	}

	@PostMapping(value = { "/register" }, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public void registerNewUser(@RequestBody Map<String, Object> registrationDetails, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HttpStatus status = null;
			response.setContentType("application/json");
			if ((registrationDetails == null) || (registrationDetails.isEmpty())) {
				response.setStatus(400);
				response.getOutputStream().println("{ \"error\": \" Blank form received \"}");
			}
			String firstName = (String) registrationDetails.get("firstName");
			String lastName = (String) registrationDetails.get("lastName");
			String emailAddress = (String) registrationDetails.get("emailAddress");
			String companyName = (String) registrationDetails.get("clientName");
			String companyURL = (String) registrationDetails.get("companyURL");
			String zipCode = (String) registrationDetails.get("zipCode");
			String contactNumber = (String) registrationDetails.get("contactNumber");
			Integer employeeStrength = (Integer) registrationDetails.get("employeeStrength");
			String password = (String) registrationDetails.get("password");
			String confirmPassword = (String) registrationDetails.get("confirmPassword");
			
			// Comment out the below Debug log statements before pushing it to Production
			logger.debug("Received details of the registered user are {}, {} and {}", emailAddress, password,
					contactNumber);

			if (!password.equals(confirmPassword)) {
				// Comment out the below Debug log statements before pushing it to Production
				logger.debug("Passwords given in the fields Password and Confirm Password do not match");
				response.setStatus(400);
				response.getOutputStream().println("{ \"error\": \" Passwords do not match \"}");
				return;
			}
			logger.debug("Creating the user account");

			Calendar currentTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

			Client newClient = this.clientRepository.findByClientName(companyName);

			if (newClient == null) {
				logger.info("Company is not yet Registered with us. Creating a new record to be sent for approval");

				Client clientAccount = new Client();
				clientAccount.setEmailAddress(emailAddress);
				clientAccount.setClientName(companyName);
				clientAccount.setCompanyURL(companyURL);
				clientAccount.setPhoneNumber(contactNumber);
				clientAccount.setEmployeeStrength(employeeStrength);
				clientAccount.setActive(true);

				newClient = this.clientRepository.save(clientAccount);
			}

			Address clientAddress = new Address();
			clientAddress.setClient(newClient);
			clientAddress.setZipCode(zipCode);
			clientAddress.setCreatedOn(currentTime.getTime());
			this.addressRepository.save(clientAddress);

			Collection<Roles> roles = new ArrayList<Roles>();
			String encodedPassword = this.userAccountService.getEncodedPassword(password);
			UserAccount newUser = new UserAccount();
			newUser.setUserName(emailAddress);
			newUser.setPassword(encodedPassword);
			newUser.setActive(true);
			newUser.setRoles(roles);
			newUser.setUserType("ADMIN");
			newUser.setAccountStatus("PENDING");
			newUser.setCreatedOn(currentTime.getTime());
			this.userAccountRepository.save(newUser);

			EmployeeDetails empClientDetails = new EmployeeDetails();
			empClientDetails.setFirstName(firstName);
			empClientDetails.setLastName(lastName);

			AccountApproval approval = new AccountApproval();
			approval.setStatus("PENDING");
			approval.setEmail(newClient.getEmailAddress());

			Client adminAccount = this.clientRepository
					.findByEmailAddress(this.mailTemplateConfiguration.getMailSuperAdmins().trim());
			approval.setClientAccount(adminAccount);

/*			this.accountApprovalRepository.save(approval);
			logger.info("Pending Approval status saved successfully");*/

			AccountCreationEmail mail = new AccountCreationEmail();
			logger.info("Calling AccountCreationEmail to send new registration: " + mail);
			mail.setMailTo(this.mailTemplateConfiguration.getMailSuperAdmins());
			mail.setMailFrom("<no-reply>@hrms.net");
			mail.setMailSubject("New User Accout Created");

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("userName", newClient.getEmailAddress());
			model.put("signature", "www.hrms.com");
			mail.setModel(model);

			this.emailHandler.sendEmailToAdmin(mail);

			logger.debug("Return status : " + status);
			response.setStatus(200);
			response.getOutputStream().println("{ \"Success\": \" Account creation successful. \"}");
		} catch (IOException ex) {
			response.setStatus(500);
			try {
				response.getOutputStream().println("{ \"Error\": \" Internal Server Error. " + ex.getMessage() + "\"}");
			} catch (IOException e) {
				logger.error(e.getMessage());
				return;
			}
		}
	}

	@GetMapping(value = { "/regitrationConfirm" })
	public void confirmRegistration(WebRequest request, @RequestParam("token") String token,
			HttpServletResponse response) throws IOException {
		logger.info("Activing profile..");
		TokenVerification tokenVerification = this.clientService.getTokenVerification(token);
		if (tokenVerification == null) {
			response.setStatus(400);
			response.getOutputStream().println("{ \"Error\": \" Invalid token (null) \"}");
		}
		Client clientUser = tokenVerification.getClientAccount();
		Calendar cal = Calendar.getInstance();
		if (tokenVerification.getExpirationDate().getTime() - cal.getTime().getTime() <= 0L) {
			logger.error("user expired already on " + tokenVerification.getExpirationDate().getTime());
			response.setStatus(400);
			response.getOutputStream().println("{ \"Error\": \" user expired already on "
					+ tokenVerification.getExpirationDate().getTime() + " \"}");
		}
		clientUser.setActive(true);
		clientUser.setAccountExpired(false);
		clientUser.setAccountLocked(false);
		this.clientService.addClient(clientUser);
		logger.info("Profile activated successfully, the user can now login..");
		response.setStatus(200);
		response.getOutputStream()
				.println("{ \"Success\": \" Account acivated successfully, the user can now login \"}");
	}
}
