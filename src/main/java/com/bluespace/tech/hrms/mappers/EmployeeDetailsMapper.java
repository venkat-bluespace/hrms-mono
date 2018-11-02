package com.bluespace.tech.hrms.mappers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.bson.types.Binary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.bluespace.tech.hrms.domain.employee.EmployeeDetails;
import com.bluespace.tech.hrms.dto.EmployeeDetailsDTO;

public class EmployeeDetailsMapper {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDetailsMapper.class);
	
	public static EmployeeDetails mapDTOToEntity(EmployeeDetailsDTO empDetailsDTO, long id) {
		EmployeeDetails empDetails = new EmployeeDetails();
		empDetails.setEmployeeId(id);
		empDetails.setFirstName(empDetailsDTO.getFirstName());
		empDetails.setLastName(empDetailsDTO.getLastName());
		empDetails.setFirstName(empDetailsDTO.getFirstName());
		empDetails.setMiddleName(empDetailsDTO.getMiddleName());
		empDetails.setLegalName(empDetailsDTO.getLegalName());
		empDetails.setEmailId(empDetailsDTO.getEmailId());
		empDetails.setPrimaryEmail(empDetailsDTO.getPrimaryEmail());
		empDetails.setSecondaryEmail(empDetailsDTO.getSecondaryEmail());
		empDetails.setMobileNumber(empDetailsDTO.getMobileNumber());
		empDetails.setHomePhoneNumber(empDetailsDTO.getHomePhoneNumber());
		empDetails.setWorkPhoneNumber(empDetailsDTO.getWorkPhoneNumber());
		empDetails.setSsn(empDetailsDTO.getSsn());
		empDetails.setDateOfBirth(empDetailsDTO.getDateOfBirth());
		empDetails.setGender(empDetailsDTO.getGender());
		empDetails.setProfileImage(insert(empDetailsDTO.getProfileImage()));
		empDetails.setPrimaryContact(empDetailsDTO.getPrimaryContact());
		empDetails.setPrimaryContactAltPhone(empDetailsDTO.getPrimaryContactAltPhone());
		empDetails.setPrimaryContactPhone(empDetailsDTO.getPrimaryContactPhone());
		empDetails.setPrimaryContactRelation(empDetailsDTO.getPrimaryContactRelation());
		empDetails.setPrimaryEmail(empDetailsDTO.getPrimaryEmail());
		empDetails.setSecondaryContact(empDetailsDTO.getSecondaryContact());
		empDetails.setSecondaryContactAltPhone(empDetailsDTO.getSecondaryContactAltPhone());
		empDetails.setSecondaryContactPhone(empDetailsDTO.getSecondaryContactPhone());
		empDetails.setSecondaryContactRelation(empDetailsDTO.getSecondaryContactRelation());
		empDetails.setHireDate(empDetailsDTO.getHireDate());
		empDetails.setTerminationDate(empDetailsDTO.getTerminationDate());
		empDetails.setEmploymentLastDate(empDetailsDTO.getEmploymentLastDate());
		empDetails.setClientName(empDetailsDTO.getClientName());
		empDetails.setCurrentStatus(empDetailsDTO.getCurrentStatus());
		empDetails.setJobTitle(empDetailsDTO.getJobTitle());
		empDetails.setOrganisation(empDetailsDTO.getOrganisation());
		empDetails.setDepartment(empDetailsDTO.getDepartment());
		empDetails.setEmpSalary(empDetailsDTO.getEmpSalary());
		empDetails.setEmploymentStatus(empDetailsDTO.getEmploymentStatus());
		empDetails.setEmploymentType(empDetailsDTO.getEmploymentType());
		empDetails.setReportingManager(empDetailsDTO.getReportingManager());
		empDetails.setActive(empDetailsDTO.isActive());
		empDetails.setCreatedBy(empDetailsDTO.getCreatedBy());
		empDetails.setCreatedOn(empDetailsDTO.getCreatedOn());
		empDetails.setModifiedBy(empDetailsDTO.getModifiedBy());
		empDetails.setModifiedOn(empDetailsDTO.getModifiedOn());
		return empDetails;
	}
	public static EmployeeDetailsDTO mapEntityToDTO(EmployeeDetails empDetailsDTO)
	{
		EmployeeDetailsDTO empDetails= new EmployeeDetailsDTO();
		empDetails.setEmployeeId(empDetailsDTO.getEmployeeId());
		empDetails.setFirstName(empDetailsDTO.getFirstName());
		empDetails.setLastName(empDetailsDTO.getLastName());
		empDetails.setFirstName(empDetailsDTO.getFirstName());
		empDetails.setMiddleName(empDetailsDTO.getMiddleName());
		empDetails.setLegalName(empDetailsDTO.getLegalName());
		empDetails.setEmailId(empDetailsDTO.getEmailId());
		empDetails.setPrimaryEmail(empDetailsDTO.getPrimaryEmail());
		empDetails.setSecondaryEmail(empDetailsDTO.getSecondaryEmail());
		empDetails.setMobileNumber(empDetailsDTO.getMobileNumber());
		empDetails.setHomePhoneNumber(empDetailsDTO.getHomePhoneNumber());
		empDetails.setWorkPhoneNumber(empDetailsDTO.getWorkPhoneNumber());
		empDetails.setSsn(empDetailsDTO.getSsn());
		empDetails.setDateOfBirth(empDetailsDTO.getDateOfBirth());
		empDetails.setGender(empDetailsDTO.getGender());
		empDetails.setProfileImage(retrieve(empDetailsDTO.getProfileImage()));
		empDetails.setPrimaryContact(empDetailsDTO.getPrimaryContact());
		empDetails.setPrimaryContactAltPhone(empDetailsDTO.getPrimaryContactAltPhone());
		empDetails.setPrimaryContactPhone(empDetailsDTO.getPrimaryContactPhone());
		empDetails.setPrimaryContactRelation(empDetailsDTO.getPrimaryContactRelation());
		empDetails.setPrimaryEmail(empDetailsDTO.getPrimaryEmail());
		empDetails.setSecondaryContact(empDetailsDTO.getSecondaryContact());
		empDetails.setSecondaryContactAltPhone(empDetailsDTO.getSecondaryContactAltPhone());
		empDetails.setSecondaryContactPhone(empDetailsDTO.getSecondaryContactPhone());
		empDetails.setSecondaryContactRelation(empDetailsDTO.getSecondaryContactRelation());
		empDetails.setHireDate(empDetailsDTO.getHireDate());
		empDetails.setTerminationDate(empDetailsDTO.getTerminationDate());
		empDetails.setEmploymentLastDate(empDetailsDTO.getEmploymentLastDate());
		empDetails.setClientName(empDetailsDTO.getClientName());
		empDetails.setCurrentStatus(empDetailsDTO.getCurrentStatus());
		empDetails.setJobTitle(empDetailsDTO.getJobTitle());
		empDetails.setOrganisation(empDetailsDTO.getOrganisation());
		empDetails.setDepartment(empDetailsDTO.getDepartment());
		empDetails.setEmpSalary(empDetailsDTO.getEmpSalary());
		empDetails.setEmploymentStatus(empDetailsDTO.getEmploymentStatus());
		empDetails.setEmploymentType(empDetailsDTO.getEmploymentType());
		empDetails.setReportingManager(empDetailsDTO.getReportingManager());
		empDetails.setActive(empDetailsDTO.isActive());
		empDetails.setCreatedBy(empDetailsDTO.getCreatedBy());
		empDetails.setCreatedOn(empDetailsDTO.getCreatedOn());
		empDetails.setModifiedBy(empDetailsDTO.getModifiedBy());
		empDetails.setModifiedOn(empDetailsDTO.getModifiedOn());
		return empDetails;
	}

	static Binary insert(MultipartFile filename) {
		final Path rootLocation = Paths.get("resources");

		System.out.println(filename.getOriginalFilename());
		System.out.println(rootLocation.toUri());
		
		try {
			Files.copy(filename.getInputStream(), rootLocation.resolve(filename.getOriginalFilename()));
		} catch (IOException e) {
			logger.error("Multipart failed with exception: " + e);
		}

		Binary data = null;
		byte b[] = Base64.decodeBase64(filename.getOriginalFilename());
		data = new Binary(b);
		return data;
	}

	public static MultipartFile retrieve(Binary filename) {
		String data = null;
		byte imageData[] = filename.getData();
		data = Base64.encodeBase64String(imageData);
		logger.info("Data of the multipart file: " + data);

		MultipartFile multiPart = null;
		return multiPart;
	}

	public static EmployeeDetailsDTO convertRequestToObject(HttpServletRequest request) throws ParseException {
		EmployeeDetailsDTO dto = new EmployeeDetailsDTO();
		if (request.getParameter("firstName") != null) {
			dto.setFirstName(request.getParameter("firstName"));
		}
		if (request.getParameter("middleName") != null) {
			dto.setMiddleName(request.getParameter("middleName"));
		}
		if (request.getParameter("lastName") != null) {
			dto.setLastName(request.getParameter("lastName"));
		}
		if (request.getParameter("legalName") != null) {
			dto.setLegalName(request.getParameter("legalName"));
		}
		if (request.getParameter("emailId") != null) {
			dto.setEmailId(request.getParameter("emailId"));
		}
		if (request.getParameter("mobileNumber") != null) {
			dto.setMobileNumber(request.getParameter("mobileNumber"));
		}
		if (request.getParameter("ssn") != null) {
			dto.setSsn(request.getParameter("ssn"));
		}
		if (request.getParameter("dateOfBirth") != null) {
			dto.setDateOfBirth(new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dateOfBirth")));
		}
		if (request.getParameter("gender") != null) {
			dto.setGender(request.getParameter("gender"));
		}
		if (request.getParameter("primaryEmail") != null) {
			dto.setPrimaryEmail(request.getParameter("primaryEmail"));
		}
		if (request.getParameter("secondaryEmail") != null) {
			dto.setSecondaryEmail(request.getParameter("secondaryEmail"));
		}
		if (request.getParameter("homePhoneNumber") != null) {
			dto.setHomePhoneNumber(request.getParameter("homePhoneNumber"));
		}
		if (request.getParameter("workPhoneNumber") != null) {
			dto.setWorkPhoneNumber(request.getParameter("workPhoneNumber"));
		}
		if (request.getParameter("primaryContact") != null) {
			dto.setPrimaryContact(request.getParameter("primaryContact"));
		}
		if (request.getParameter("primaryContactRelation") != null) {
			dto.setPrimaryContactRelation(request.getParameter("primaryContactRelation"));
		}
		if (request.getParameter("primaryContactPhone") != null) {
			dto.setPrimaryContactPhone(request.getParameter("primaryContactPhone"));
		}
		if (request.getParameter("primaryContactAltPhone") != null) {
			dto.setPrimaryContactAltPhone(request.getParameter("primaryContactAltPhone"));
		}
		if (request.getParameter("secondaryContact") != null) {
			dto.setSecondaryContact(request.getParameter("secondaryContact"));
		}
		if (request.getParameter("secondaryContactRelation") != null) {
			dto.setSecondaryContactRelation(request.getParameter("secondaryContactRelation"));
		}
		if (request.getParameter("secondaryContactPhone") != null) {
			dto.setSecondaryContactPhone(request.getParameter("secondaryContactPhone"));
		}
		if (request.getParameter("secondaryContactAltPhone") != null) {
			dto.setSecondaryContactAltPhone(request.getParameter("secondaryContactAltPhone"));
		}
		if (request.getParameter("hireDate") != null) {
			if (!request.getParameter("hireDate").isEmpty()) {
				logger.info("Hire Date is: " + request.getParameter("hireDate"));
				dto.setHireDate(new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("hireDate")));
				logger.info("Hire Date is: " + dto.getHireDate());
			}
		}
		if (request.getParameter("terminationDate") != null) {
			if (!request.getParameter("terminationDate").isEmpty()) {
				dto.setTerminationDate(
						new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("terminationDate")));
			}
		}
		if (request.getParameter("employmentLastDate") != null) {
			if (!request.getParameter("employmentLastDate").isEmpty()) {
				dto.setEmploymentLastDate(
						new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("employmentLastDate")));
			}
		}
		if (request.getParameter("clientName") != null) {
			dto.setClientName(request.getParameter("clientName"));
		}
		if (request.getParameter("currentStatus") != null) {
			dto.setCurrentStatus(request.getParameter("currentStatus"));
		}
		if (request.getParameter("jobTitle") != null) {
			dto.setJobTitle(request.getParameter("jobTitle"));
		}
		if (request.getParameter("organisation") != null) {
			dto.setOrganisation(request.getParameter("organisation"));
		}
		if (request.getParameter("department") != null) {
			dto.setDepartment(request.getParameter("department"));
		}
		if (request.getParameter("empSalary") != null) {
			if (!request.getParameter("empSalary").isEmpty()) {
				dto.setEmpSalary(Double.parseDouble(request.getParameter("empSalary")));
			}
		}
		if (request.getParameter("employmentType") != null) {
			dto.setEmploymentType(request.getParameter("employmentType"));
		}
		if (request.getParameter("employmentStatus") != null) {
			dto.setEmploymentStatus(request.getParameter("employmentStatus"));
		}
		if (request.getParameter("reportingManager") != null) {
			dto.setReportingManager(request.getParameter("reportingManager"));
		}
		if (request.getParameter("active") != null) {
			if (!request.getParameter("active").isEmpty()) {
				dto.setActive(Boolean.parseBoolean(request.getParameter("active")));
			}
		}
		return dto;
	}
}
