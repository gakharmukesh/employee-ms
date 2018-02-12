package com.mukesh.ms.lab.employee.resources.mapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;


import com.mukesh.ms.lab.employee.resources.models.Employee;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;


import junit.framework.TestCase;

public class EmployeeMapperTest extends TestCase 
{

	private static EmployeeDTO dto;
	private static Employee emp;
	private static List<EmployeeDTO> dtoList;
	private static LocalDate firstDay_2018=LocalDate.of(2018, Month.JANUARY, 1);
	private static LocalDate firstDay_2017=LocalDate.of(2017, Month.JANUARY, 1);
	private static EmployeeMapper mapper;
	private static BigDecimal decimal;
	
	@BeforeClass
	public void setUp() throws Exception
	{
		decimal=new BigDecimal(101);
		dto=new EmployeeDTO(101,"Mukesh",null,"Kumar",firstDay_2018,firstDay_2018,"ACTIVE");
		emp=new Employee();
		emp.setId(decimal);
		emp.setFirstName("Mukesh");
		emp.lastName("Kumar");
		emp.setDateOfBirth(firstDay_2018);
		emp.setDateOfEmployment(firstDay_2018);
		emp.setStatus("ACTIVE");
		emp.setId(decimal);
		emp.setFirstName("Rahul");
		emp.setMiddleInitial("K");
		emp.lastName("Gakhar");
		emp.setDateOfBirth(firstDay_2017);
		emp.setDateOfEmployment(firstDay_2017);
		emp.setStatus("ACTIVE");

		dtoList=new ArrayList<EmployeeDTO>();
		dtoList.add(dto);
		
		mapper=new EmployeeMapper();
	}
	
	@Test
	public void testMapToEntity()
	{
		Employee emp=mapper.mapToEmployee(dto);
		
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
	public void testMapToDTO()
	{
		EmployeeDTO newDTO=mapper.mapToDTO(emp);
		
		assertNotNull(newDTO);
		assertEquals(emp.getId().longValue(),newDTO.getId());
		assertEquals(emp.getFirstName(),newDTO.getFirstName());
		assertEquals(emp.getMiddleInitial(), newDTO.getMiddleInitial());
		assertEquals(emp.getLastName(),newDTO.getLastName());
		assertEquals(emp.getDateOfBirth(), newDTO.getDateOfBirth());
		assertEquals(emp.getDateOfEmployment(), newDTO.getDateOfEmployment());
		assertEquals(emp.getStatus(),newDTO.getStatus());
	}
	
	@Test
	public void testMapList()
	{
		
		List<Employee>  empList=mapper.mapToEmployeeList(dtoList);
		
		assertNotNull(empList);
		assertTrue(empList.size()==1);
		
		Employee emp=empList.get(0);
		
		assertNotNull(emp);
		assertEquals(dto.getId(),emp.getId().longValue());
		assertEquals(dto.getFirstName(),emp.getFirstName());
		assertEquals(dto.getMiddleInitial(), emp.getMiddleInitial());
		assertEquals(dto.getLastName(),emp.getLastName());
		assertEquals(dto.getDateOfBirth(), emp.getDateOfBirth());
		assertEquals(dto.getDateOfEmployment(), emp.getDateOfEmployment());
		assertEquals(dto.getStatus(),emp.getStatus());
		
	}
	
}
