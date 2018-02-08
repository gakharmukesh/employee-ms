package com.mukesh.ms.lab.employee.services.config;

import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mukesh.ms.lab.employee.services.errorhandling.impl.ServiceErrorHandlerAspect;
import com.mukesh.ms.lab.employee.services.errorhandling.impl.ServiceInterceptor;
import com.mukesh.ms.lab.employee.services.errorhandling.interfaces.IServiceInterceptor;
import com.mukesh.ms.lab.employee.services.impl.EmployeeServiceImpl;
import com.mukesh.ms.lab.employee.services.impl.Messages;
import com.mukesh.ms.lab.employee.services.interfaces.IMessages;


@Configuration
public class ApplicationServicesConfig 
{

	 @Bean
	 public IServiceInterceptor getServiceInterceptor() {
	        return new ServiceInterceptor();
	    }

	 @Bean
	 public ServiceErrorHandlerAspect getServiceErrorHandlerAspect() {
	        return new ServiceErrorHandlerAspect();
	    }

	
	 @Bean
	 public IMessages getMessages()
	 {
		 return new Messages();
	 }
}
