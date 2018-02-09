package com.mukesh.ms.lab.employee.resources.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mukesh.ms.lab.employee.repository.EmployeeRepository;
import com.mukesh.ms.lab.employee.resources.enums.OperationType;
import com.mukesh.ms.lab.employee.resources.mapper.EmployeeMapper;
import com.mukesh.ms.lab.employee.resources.models.Employee;
import com.mukesh.ms.lab.employee.resources.models.ValidationFailureCollection;
import com.mukesh.ms.lab.employee.resources.validation.impl.EmployeeInputValidationImpl;
import com.mukesh.ms.lab.employee.resources.validation.interfaces.IEmployeeInputValidation;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;
import com.mukesh.ms.lab.employee.services.interfaces.IEmployeeService;

import junit.framework.TestCase;


public class EmployeeApiImplTest extends TestCase
{

	EmployeesApiImpl api;
	
	private static IEmployeeService service;
	private static IEmployeeInputValidation validator;
	private static EmployeeDTO dto;
	private static Employee emp;
	
	private static LocalDate firstDay_2018=LocalDate.of(2018, Month.JANUARY, 1);
	private static LocalDate firstDay_2017=LocalDate.of(2017, Month.JANUARY, 1);
	private final static String converstaionId="dsfsdfsdf";
	
	@Before
	public void setUp() throws Exception
	{
		service=mock(IEmployeeService.class);
		validator=mock(IEmployeeInputValidation.class);
		dto= new EmployeeDTO(101,"Mukesh","K","Kumar",firstDay_2018,firstDay_2018,"ACTIVE");
		emp= EmployeeMapper.mapToEmployee(dto);
		List<EmployeeDTO> allEmployees = Collections.singletonList(dto);
		when(service.getEmployees()).thenReturn(allEmployees);
		when(service.getEmployee(101)).thenReturn(dto);
		when(service.addEmployee(dto)).thenReturn(dto);
		
		when(validator.validateRequest(null, emp, OperationType.CREATE)).thenReturn(new ValidationFailureCollection());
		api=new EmployeesApiImpl();
		api.setService(service);
		api.setEmployeeInputValidation(validator);
		
	}

	@Test
	public void testGetEmployees() throws Exception {		
		
		ResponseEntity<List<Employee>> response =api.getEmployees(converstaionId);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertTrue(response.getBody().size()==1);
		
		Employee emp=response.getBody().get(0);
		
		assertNotNull(emp);
		assertEquals(dto.getId(),emp.getId().longValue());
		assertEquals(dto.getFirstName(),emp.getFirstName());
		assertEquals(dto.getMiddleInitial(), emp.getMiddleInitial());
		assertEquals(dto.getLastName(),emp.getLastName());
		assertEquals(dto.getDateOfBirth(), emp.getDateOfBirth());
		assertEquals(dto.getDateOfEmployment(), emp.getDateOfEmployment());
		assertEquals(dto.getStatus(),emp.getStatus());
		
		

	}
	
	@Test
	public void testGetEmployeeByID() throws Exception {		
		
		ResponseEntity<Object> response =api.getEmployeeByID(new BigDecimal(101), converstaionId);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
			
		Employee emp=(Employee)response.getBody();
		
		assertNotNull(emp);
		assertEquals(dto.getId(),emp.getId().longValue());
		assertEquals(dto.getFirstName(),emp.getFirstName());
		assertEquals(dto.getMiddleInitial(), emp.getMiddleInitial());
		assertEquals(dto.getLastName(),emp.getLastName());
		assertEquals(dto.getDateOfBirth(), emp.getDateOfBirth());
		assertEquals(dto.getDateOfEmployment(), emp.getDateOfEmployment());
		assertEquals(dto.getStatus(),emp.getStatus());
		
		

	}
	
	@Test
	public void testaddEmployee() throws Exception {		
		
	/*	ResponseEntity<Object> response =api.addEmployee(emp, converstaionId);
		
		assertNotNull(response);
		assertNotNull(response.getBody());			
		Employee emp=(Employee)response.getBody();		
		assertNotNull(emp);
		assertEquals(dto.getId(),emp.getId().longValue());
		assertEquals(dto.getFirstName(),emp.getFirstName());
		assertEquals(dto.getMiddleInitial(), emp.getMiddleInitial());
		assertEquals(dto.getLastName(),emp.getLastName());
		assertEquals(dto.getDateOfBirth(), emp.getDateOfBirth());
		assertEquals(dto.getDateOfEmployment(), emp.getDateOfEmployment());
		assertEquals(dto.getStatus(),emp.getStatus());*/
		
	}
	
	
	
	
}
