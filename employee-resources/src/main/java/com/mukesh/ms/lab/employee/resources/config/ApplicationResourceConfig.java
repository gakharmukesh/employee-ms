package com.mukesh.ms.lab.employee.resources.config;


import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.mukesh.ms.lab.employee.resources.errorhandling.impl.ResourceErrorHandlerAspect;
import com.mukesh.ms.lab.employee.resources.errorhandling.impl.ResourceInterceptor;
import com.mukesh.ms.lab.employee.resources.errorhandling.interfaces.IResourceInterceptor;
import com.mukesh.ms.lab.employee.resources.mapper.EmployeeMapper;
import com.mukesh.ms.lab.employee.resources.validation.impl.EmployeeInputValidationImpl;
import com.mukesh.ms.lab.employee.resources.validation.interfaces.IEmployeeInputValidation;
import com.mukesh.ms.lab.employee.services.impl.EmployeeServiceImpl;
import com.mukesh.ms.lab.employee.services.interfaces.IEmployeeService; 

@Configuration
public class ApplicationResourceConfig 
{
	
	@Bean
	public EmployeeMapper mapper() {
		return new EmployeeMapper();
	}

	@Bean
	public IEmployeeService service() {

		return new EmployeeServiceImpl();
	}

	@Bean
	@Scope(value = "prototype")
	public IEmployeeInputValidation employeeInputValidation() {
		return new EmployeeInputValidationImpl();
	}

	@Bean
	public IResourceInterceptor getResourceInterceptor() {
		return new ResourceInterceptor();
	}

	@Bean
	public ResourceErrorHandlerAspect getResourceErrorHandlerAspect() {
		return new ResourceErrorHandlerAspect();
	}
	
/*	@Bean
	BasicAuthenticationFilter basicAuthFilter(AuthenticationManager authenticationManager, BasicAuthenticationEntryPoint basicAuthEntryPoint) {
		
	  return new BasicAuthenticationFilter(authenticationManager, basicAuthEntryPoint());
	}
	@Bean
	BasicAuthenticationEntryPoint basicAuthEntryPoint() {
	  BasicAuthenticationEntryPoint bauth = new BasicAuthenticationEntryPoint();
	  bauth.setRealmName("USERS");
      return bauth;
	}*/
}
