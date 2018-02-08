package com.mukesh.ms.lab.employee.resources.config;

import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.mukesh.ms.lab.employee.resources.errorhandling.impl.ResourceErrorHandlerAspect;
import com.mukesh.ms.lab.employee.resources.errorhandling.impl.ResourceInterceptor;
import com.mukesh.ms.lab.employee.resources.errorhandling.interfaces.IResourceInterceptor;
import com.mukesh.ms.lab.employee.resources.validation.impl.EmployeeInputValidationImpl;
import com.mukesh.ms.lab.employee.resources.validation.interfaces.IEmployeeInputValidation;
import com.mukesh.ms.lab.employee.services.impl.EmployeeServiceImpl;
import com.mukesh.ms.lab.employee.services.interfaces.IEmployeeService; 

@Configuration
public class ApplicationResourceConfig 
{
	@Bean
	public IEmployeeService service() {
		System.out.println("Service created");
		return new EmployeeServiceImpl();
	}
	
	@Bean
	@Scope(value ="prototype")
	public IEmployeeInputValidation employeeInputValidation()
	{
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
