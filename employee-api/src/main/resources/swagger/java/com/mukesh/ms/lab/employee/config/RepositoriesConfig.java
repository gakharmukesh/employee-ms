package com.mukesh.ms.lab.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mukesh.ms.lab.employee.repository.EmployeeRepository;

@Configuration
@EnableJpaRepositories(basePackages = {"com.mukesh.ms.lab.employee.repository"})
public class RepositoriesConfig 
{
    
	
	//abstract public EmployeeRepository employeeRepository();
	
}
