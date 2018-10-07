package com.bluespace.tech.hrms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.bluespace.tech.hrms.repositories.client.ClientRepository;
import com.bluespace.tech.hrms.security.repositories.AccountApprovalRepository;

@SpringBootApplication(scanBasePackages = "com.bluespace.tech.hrms")
@EnableMongoRepositories(basePackageClasses = AccountApprovalRepository.class)
public class HrmsMonoApplication {

	private static Logger logger = LogManager.getLogger(HrmsMonoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HrmsMonoApplication.class, args);
		logger.info("Loading HRMS Application");
	}
	
	@Bean
	public CommandLineRunner client(ClientRepository clientRepository) {
		return null;
		
	}

}