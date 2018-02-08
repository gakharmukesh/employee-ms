package com.mukesh.ms.lab.employee.services.mapper;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.EntityDeclaration;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mukesh.ms.lab.employee.entity.EmployeeEntity;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;

import junit.framework.TestCase;

public class EmployeeEntityDTOMapperTest extends TestCase
{
 
	private static EmployeeDTO emp1;
	private static EmployeeDTO emp2;
	private static EmployeeEntity entity;
	private static EmployeeEntity entity1;
	private static List<EmployeeEntity> entityList;
	private static LocalDate firstDay_2018=LocalDate.of(2018, Month.JANUARY, 1);
	private static LocalDate firstDay_2017=LocalDate.of(2017, Month.JANUARY, 1);
	
	@BeforeClass
	public void setUp() throws Exception
	{
		
		emp1=new EmployeeDTO(101,"Mukesh",null,"Kumar",firstDay_2018,firstDay_2018,"ACTIVE");
		entity=new EmployeeEntity(101,"Mukesh",null,"Kumar",firstDay_2018,firstDay_2018,"ACTIVE");
		emp2=new EmployeeDTO(101,"Rahul","K","Gakhar",firstDay_2017,firstDay_2017,"ACTIVE");
		entity1=new EmployeeEntity(101,"Rahul","K","Gakhar",firstDay_2017,firstDay_2017,"INACTIVE");
		entityList=new ArrayList<EmployeeEntity>();
		entityList.add(entity);
	}
	
	@Test
	public void testMapToEntity()
	{
		EmployeeEntity newEntity=EmployeeEntityDTOMapper.mapToEntity(emp1);
		
		assertNotNull(newEntity);
		assertEquals(emp1.getId(),newEntity.getId());
		assertEquals(emp1.getFirstName(),newEntity.getFirstName());
		assertEquals(emp1.getMiddleInitial(), newEntity.getMiddleInitial());
		assertEquals(emp1.getLastName(),newEntity.getLastName());
		assertEquals(emp1.getDateOfBirth(), newEntity.getDateOfBirth());
		assertEquals(emp1.getDateOfEmployment(), newEntity.getDateOfEmployment());
		//assertEquals(entity.getStatus(),newEntity.getStatus());
	}
	
	@Test
	public void testMapToDTO()
	{
		EmployeeDTO newDTO=EmployeeEntityDTOMapper.mapToDTO(entity);
		
		assertNotNull(newDTO);
		assertEquals(entity.getId(),newDTO.getId());
		assertEquals(entity.getFirstName(),newDTO.getFirstName());
		assertEquals(entity.getMiddleInitial(), newDTO.getMiddleInitial());
		assertEquals(entity.getLastName(),newDTO.getLastName());
		assertEquals(entity.getDateOfBirth(), newDTO.getDateOfBirth());
		assertEquals(entity.getDateOfEmployment(), newDTO.getDateOfEmployment());
		assertEquals(entity.getStatus(),newDTO.getStatus());
	}
	
	@Test
	public void testMapList()
	{
		List<EmployeeDTO> dtoList=new ArrayList<EmployeeDTO>();
		dtoList=EmployeeEntityDTOMapper.mapList(entityList, dtoList);
		
		assertNotNull(dtoList);
		assertTrue(dtoList.size()==1);
		
		EmployeeDTO newDTO=dtoList.get(0);
		
		assertNotNull(newDTO);
		assertEquals(entity.getId(),newDTO.getId());
		assertEquals(entity.getFirstName(),newDTO.getFirstName());
		assertEquals(entity.getMiddleInitial(), newDTO.getMiddleInitial());
		assertEquals(entity.getLastName(),newDTO.getLastName());
		assertEquals(entity.getDateOfBirth(), newDTO.getDateOfBirth());
		assertEquals(entity.getDateOfEmployment(), newDTO.getDateOfEmployment());
		assertEquals(entity.getStatus(),newDTO.getStatus());
		
	}
	
}
