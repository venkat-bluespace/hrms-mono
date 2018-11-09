package com.bluespace.tech.hrms.domain.member.details;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="compensationdetails")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompensationDetails {

    enum EmploymentType {
        FULLTIME,HOURLY
    }
    
    @Id private int id;
    private List<Lead> lead;
    private String currencyCode;
    private Double hourlyRate;
    private EmploymentType  employmentDetail;
	
}
