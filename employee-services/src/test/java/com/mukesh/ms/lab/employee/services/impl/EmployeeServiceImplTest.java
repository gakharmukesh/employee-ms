package com.mukesh.ms.lab.employee.services.impl;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mukesh.ms.lab.employee.entity.EmployeeEntity;
import com.mukesh.ms.lab.employee.repository.EmployeeRepository;
import com.mukesh.ms.lab.employee.repository.enums.EmployeeStatus;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;
import com.mukesh.ms.lab.employee.services.interfaces.IEmployeeService;

import junit.framework.TestCase;

public class EmployeeServiceImplTest extends TestCase 
{
 
	private static EmployeeDTO emp1;
	private static EmployeeDTO emp2;
	private static EmployeeEntity entity;
	private static EmployeeEntity entity1;
	private static EmployeeRepository repository;
	private static LocalDate firstDay_2018=LocalDate.of(2018, Month.JANUARY, 1);
	private static LocalDate firstDay_2017=LocalDate.of(2017, Month.JANUARY, 1);
	private static EmployeeServiceImpl service;
	
	
	@BeforeClass
	public void setUp() throws Exception
	{
		
		emp1=new EmployeeDTO(101,"Mukesh",null,"Kumar",firstDay_2018,firstDay_2018,"ACTIVE");
		entity=new EmployeeEntity(101,"Mukesh",null,"Kumar",firstDay_2018,firstDay_2018,"ACTIVE");
		emp2=new EmployeeDTO(101,"Rahul","K","Gakhar",firstDay_2017,firstDay_2017,"ACTIVE");
		entity1=new EmployeeEntity(101,"Rahul","K","Gakhar",firstDay_2017,firstDay_2017,"INACTIVE");
		List<EmployeeEntity> entityList=new ArrayList<EmployeeEntity>();
		entityList.add(entity);
		repository=mock(EmployeeRepository.class);
		when(repository.findByIdAndStatus(101, EmployeeStatus.ACTIVE.name())).thenReturn(entity);
		when(repository.findByStatus(EmployeeStatus.ACTIVE.name())).thenReturn(entityList);
		when(repository.save(entity)).thenReturn(entity);
		//when(repository.save(entity1)).thenReturn(entity1);
		when(repository.getOne((long)101)).thenReturn(entity);
		
		service =new EmployeeServiceImpl();
		service.setEmployeeRepository(repository);
	}
	
	@Test
	public void testGetEmployeebyId()
	{
		
		EmployeeDTO dto=service.getEmployee(101);
		
		assertNotNull(dto);
		assertEquals(entity.getId(),dto.getId());
		assertEquals(entity.getFirstName(),dto.getFirstName());
		assertEquals(entity.getMiddleInitial(), dto.getMiddleInitial());
		assertEquals(entity.getLastName(),dto.getLastName());
		assertEquals(entity.getDateOfBirth(), dto.getDateOfBirth());
		assertEquals(entity.getDateOfEmployment(), entity.getDateOfEmployment());
		assertEquals(entity.getStatus(),dto.getStatus());
	}
	
	@Test
	public void testGetAllEmployees()
	{
		    List<EmployeeDTO> dtoList=service.getEmployees();
		    
		    assertNotNull(dtoList);
		    assertTrue(dtoList.size()==1);
		    
		    EmployeeDTO dto =dtoList.get(0);
		    
		    assertNotNull(dto);
			assertEquals(entity.getId(),dto.getId());
			assertEquals(entity.getFirstName(),dto.getFirstName());
			assertEquals(entity.getMiddleInitial(), dto.getMiddleInitial());
			assertEquals(entity.getLastName(),dto.getLastName());
			assertEquals(entity.getDateOfBirth(), dto.getDateOfBirth());
			assertEquals(entity.getDateOfEmployment(), entity.getDateOfEmployment());
			assertEquals(entity.getStatus(),dto.getStatus());
		    
	}
	
	@Test
	public void testUpdateEmployee()
	{
	  /*	EmployeeDTO dto= service.updateEmployee(101, emp2);
		
		assertNotNull(dto);
		assertEquals(entity1.getId(),dto.getId());
		assertEquals(entity1.getFirstName(),dto.getFirstName());
		assertEquals(entity1.getMiddleInitial(), dto.getMiddleInitial());
		assertEquals(entity1.getLastName(),dto.getLastName());
		assertEquals(entity1.getDateOfBirth(), dto.getDateOfBirth());
		assertEquals(entity1.getDateOfEmployment(), entity.getDateOfEmployment());
		assertEquals(entity1.getStatus(),dto.getStatus());*/
	}
	
	@Test
	public void testAddEmployee()
	{
		EmployeeDTO dto=service.addEmployee(emp1);
		
	/*	assertNotNull(dto);
		assertEquals(entity.getId(),dto.getId());
		assertEquals(entity.getFirstName(),dto.getFirstName());
		assertEquals(entity.getMiddleInitial(), dto.getMiddleInitial());
		assertEquals(entity.getLastName(),dto.getLastName());
		assertEquals(entity.getDateOfBirth(), dto.getDateOfBirth());
		assertEquals(entity.getDateOfEmployment(), entity.getDateOfEmployment());
		assertEquals(entity.getStatus(),dto.getStatus()); */
	}
	
	public void testDeleteEmployee()
	{
		EmployeeDTO dto=service.deleteEmployee((long)101);
		assertNotNull(dto);
		assertEquals(entity.getId(),dto.getId());
		assertEquals(entity.getFirstName(),dto.getFirstName());
		assertEquals(entity.getMiddleInitial(), dto.getMiddleInitial());
		assertEquals(entity.getLastName(),dto.getLastName());
		assertEquals(entity.getDateOfBirth(), dto.getDateOfBirth());
		assertEquals(entity.getDateOfEmployment(), entity.getDateOfEmployment());
		assertEquals(entity.getStatus(),"INACTIVE");
	}
	
}
