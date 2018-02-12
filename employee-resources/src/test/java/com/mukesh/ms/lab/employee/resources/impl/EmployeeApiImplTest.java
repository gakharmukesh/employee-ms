package com.mukesh.ms.lab.employee.resources.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import com.mukesh.ms.lab.employee.resources.enums.OperationType;
import com.mukesh.ms.lab.employee.resources.mapper.EmployeeMapper;
import com.mukesh.ms.lab.employee.resources.models.Employee;
import com.mukesh.ms.lab.employee.resources.models.ValidationFailureCollection;

import com.mukesh.ms.lab.employee.resources.validation.interfaces.IEmployeeInputValidation;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;
import com.mukesh.ms.lab.employee.services.interfaces.IEmployeeService;

import junit.framework.TestCase;


public class EmployeeApiImplTest extends TestCase
{

	EmployeesApiImpl api;
	
	private static IEmployeeService service;
	private static IEmployeeInputValidation validator;
	private static EmployeeMapper mapper;
	private static EmployeeDTO dto;
	private static EmployeeDTO updateDto;
	private static EmployeeDTO DeleteDto;
	private static Employee emp;
	private static Employee updateEmp;
	private static Employee deleteEmp;
	private static BigDecimal decimal;
	
	private static LocalDate firstDay_2018=LocalDate.of(2018, Month.JANUARY, 1);
	private static LocalDate firstDay_2017=LocalDate.of(2017, Month.JANUARY, 1);
	private final static String converstaionId="dsfsdfsdf";
	
	@Before
	public void setUp() throws Exception
	{
		decimal=new BigDecimal(101);
		dto= new EmployeeDTO(101,"Mukesh","K","Kumar",firstDay_2018,firstDay_2017,"ACTIVE");
		updateDto= new EmployeeDTO(101,"Rahul","K","Gakhar",firstDay_2018,firstDay_2017,"ACTIVE");
		DeleteDto= new EmployeeDTO(101,"Mukesh","K","Kumar",firstDay_2018,firstDay_2017,"INACTIVE");
		emp= new Employee();
		emp.setId(decimal);
		emp.setFirstName("Mukesh");
		emp.setMiddleInitial("K");
		emp.lastName("Kumar");
		emp.setDateOfBirth(firstDay_2018);
		emp.setDateOfEmployment(firstDay_2017);
		emp.setStatus("ACTIVE");
		updateEmp= new Employee();
		updateEmp.setId(decimal);
		updateEmp.setFirstName("Rahul");
		updateEmp.setMiddleInitial("K");
		updateEmp.lastName("Gakhar");
		updateEmp.setDateOfBirth(firstDay_2018);
		updateEmp.setDateOfEmployment(firstDay_2017);
		updateEmp.setStatus("ACTIVE");
		
		deleteEmp= new Employee();
		deleteEmp.setId(decimal);
		deleteEmp.setFirstName("Mukesh");
		deleteEmp.setMiddleInitial("K");
		deleteEmp.lastName("Kumar");
		deleteEmp.setDateOfBirth(firstDay_2018);
		deleteEmp.setDateOfEmployment(firstDay_2017);
		deleteEmp.setStatus("INACTIVE");
		
		
		
		List<Employee> empList=new ArrayList<Employee>();
		empList.add(emp);
		
		List<EmployeeDTO> dtoList=new ArrayList<EmployeeDTO>();
		dtoList.add(dto);
		
		service=mock(IEmployeeService.class);
		List<EmployeeDTO> allEmployees = Collections.singletonList(dto);
		when(service.getEmployees()).thenReturn(allEmployees);
		when(service.getEmployee(101)).thenReturn(dto);
		when(service.addEmployee(dto)).thenReturn(dto);
		when(service.updateEmployee(101, updateDto)).thenReturn(updateDto);
		when(service.deleteEmployee(101)).thenReturn(DeleteDto);
		
		validator=mock(IEmployeeInputValidation.class);
		when(validator.validateRequest(null, emp, OperationType.CREATE)).thenReturn(new ValidationFailureCollection());
		when(validator.validateRequest(decimal, emp, OperationType.CREATE)).thenReturn(new ValidationFailureCollection());
		when(validator.validateRequest(decimal, updateEmp, OperationType.UPDATE)).thenReturn(new ValidationFailureCollection());


		mapper=mock(EmployeeMapper.class);		
		when(mapper.mapToDTO(emp)).thenReturn(dto);
		when(mapper.mapToEmployee(dto)).thenReturn(emp);
		when(mapper.mapToEmployee(updateDto)).thenReturn(updateEmp);
		when(mapper.mapToDTO(updateEmp)).thenReturn(updateDto);
		when(mapper.mapToEmployee(DeleteDto)).thenReturn(deleteEmp);
		when(mapper.mapToDTO(deleteEmp)).thenReturn(DeleteDto);
			
		
		when(mapper.mapToEmployeeList(dtoList)).thenReturn(empList);
		
		api=new EmployeesApiImpl();
		api.setService(service);
		api.setEmployeeInputValidation(validator);
		api.setMapper(mapper);
		

		
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
		
		ResponseEntity<Object> response =api.addEmployee(emp, converstaionId);
		
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
	public void testupdateEmployee()
	{
		ResponseEntity<Object> response =api.updateEmployeebyID(decimal, updateEmp, converstaionId);
		
		assertNotNull(response);
		assertNotNull(response.getBody());			
		Employee emp=(Employee)response.getBody();		
		assertNotNull(emp);
		assertEquals(updateDto.getId(),emp.getId().longValue());
		assertEquals(updateDto.getFirstName(),emp.getFirstName());
		assertEquals(updateDto.getMiddleInitial(), emp.getMiddleInitial());
		assertEquals(updateDto.getLastName(),emp.getLastName());
		assertEquals(updateDto.getDateOfBirth(), emp.getDateOfBirth());
		assertEquals(updateDto.getDateOfEmployment(), emp.getDateOfEmployment());
		assertEquals(updateDto.getStatus(),emp.getStatus());
	}
	
	
	
	@Test
	public void testDeleteEmployee()
	{
		
		ResponseEntity<Object> response =api.deleteEmployeeByID(decimal,converstaionId);
		
		assertNotNull(response);
		assertNotNull(response.getBody());			
		Employee emp=(Employee)response.getBody();		
		assertNotNull(emp);
		assertEquals(DeleteDto.getId(),emp.getId().longValue());
		assertEquals(DeleteDto.getFirstName(),emp.getFirstName());
		assertEquals(DeleteDto.getMiddleInitial(), emp.getMiddleInitial());
		assertEquals(DeleteDto.getLastName(),emp.getLastName());
		assertEquals(DeleteDto.getDateOfBirth(), emp.getDateOfBirth());
		assertEquals(DeleteDto.getDateOfEmployment(), emp.getDateOfEmployment());
		assertEquals(DeleteDto.getStatus(),emp.getStatus());
	}
	
	
	
	
}
