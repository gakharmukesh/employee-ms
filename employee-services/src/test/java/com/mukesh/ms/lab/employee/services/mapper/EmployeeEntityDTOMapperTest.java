package com.mukesh.ms.lab.employee.services.mapper;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;



import org.junit.BeforeClass;
import org.junit.Test;

import com.mukesh.ms.lab.employee.entity.EmployeeEntity;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;

import junit.framework.TestCase;

public class EmployeeEntityDTOMapperTest extends TestCase
{
 
	private static EmployeeDTO emp1;
	private static EmployeeEntity entity;
	private static List<EmployeeEntity> entityList;
	private static LocalDate firstDay_2018=LocalDate.of(2018, Month.JANUARY, 1);
	private static EmployeeEntityDTOMapper mapper;
	
	@BeforeClass
	public void setUp() throws Exception
	{
		
		emp1=new EmployeeDTO(101,"Mukesh",null,"Kumar",firstDay_2018,firstDay_2018,"ACTIVE");
		entity=new EmployeeEntity(101,"Mukesh",null,"Kumar",firstDay_2018,firstDay_2018,"ACTIVE");
		
		entityList=new ArrayList<EmployeeEntity>();
		entityList.add(entity);
		
		mapper=new EmployeeEntityDTOMapper();
	}
	
	@Test
	public void testMapToEntity()
	{
		EmployeeEntity newEntity=mapper.mapToEntity(emp1);
		
		assertNotNull(newEntity);
		assertEquals(emp1.getId(),newEntity.getId());
		assertEquals(emp1.getFirstName(),newEntity.getFirstName());
		assertEquals(emp1.getMiddleInitial(), newEntity.getMiddleInitial());
		assertEquals(emp1.getLastName(),newEntity.getLastName());
		assertEquals(emp1.getDateOfBirth(), newEntity.getDateOfBirth());
		assertEquals(emp1.getDateOfEmployment(), newEntity.getDateOfEmployment());
		assertEquals(entity.getStatus(),newEntity.getStatus());
	}
	
	@Test
	public void testMapToDTO()
	{
		EmployeeDTO newDTO=mapper.mapToDTO(entity);
		
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
		
		List<EmployeeDTO>  dtoList=mapper.mapDTOList(entityList);
		
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
