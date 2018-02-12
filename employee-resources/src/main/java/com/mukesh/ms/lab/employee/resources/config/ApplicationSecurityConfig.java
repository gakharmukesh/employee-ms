package com.mukesh.ms.lab.employee.resources.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter 
{

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.DELETE)
		.authenticated()
		.and()
		.authorizeRequests()
		.anyRequest()
		.permitAll()
		.and().httpBasic();
		  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);		

		
		
		
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	}


	

	
	

}
