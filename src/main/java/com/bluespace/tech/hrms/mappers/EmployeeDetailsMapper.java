package com.bluespace.tech.hrms.mappers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.tomcat.util.codec.binary.Base64;
import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

import com.bluespace.tech.hrms.domain.employee.EmployeeDetails;
import com.bluespace.tech.hrms.dto.EmployeeDetailsDTO;

public class EmployeeDetailsMapper {

	public static EmployeeDetails mapDTOToEntity(EmployeeDetailsDTO empDetailsDTO, long id)
	{
		EmployeeDetails empDetails= new EmployeeDetails();
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
		//empDetails.setPathFile(retrieve(empDetailsDTO.getProfileImage()));
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
	static Binary insert(MultipartFile filename)
    {
		final Path rootLocation = Paths.get("resources");
		 
		   System.out.println(filename.getOriginalFilename());
		   System.out.println(rootLocation.toUri());
		   try {
			Files.copy(filename.getInputStream(), rootLocation.resolve(filename.getOriginalFilename()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		 Binary data = null;

/*         byte b[] =  Base64.decodeBase64(filename);*/
		 
         byte b[] =  Base64.decodeBase64(filename.getOriginalFilename());
         data = new Binary(b);
        return data;
    }
	public static String retrieve(Binary filename)
    {
		 String data = null;
		 byte x[]=filename.getData();
		 data=Base64.encodeBase64String(x);
        return data;
    }
}
