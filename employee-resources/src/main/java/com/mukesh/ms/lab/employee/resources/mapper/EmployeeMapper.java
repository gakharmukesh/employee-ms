package com.mukesh.ms.lab.employee.resources.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mukesh.ms.lab.employee.entity.EmployeeEntity;
import com.mukesh.ms.lab.employee.resources.models.Employee;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;

public class EmployeeMapper 
{

	public static Employee mapToEmployee(EmployeeDTO dto)
	{
		Employee employee=new Employee();
		employee.setId(new BigDecimal(dto.getId()));
		employee.setFirstName(dto.getFirstName());
		employee.setMiddleInitial(dto.getMiddleInitial());
		employee.setLastName(dto.getLastName());
		employee.setDateOfBirth(dto.getDateOfBirth());
		employee.setDateOfEmployment(dto.getDateOfEmployment());
		employee.setStatus(dto.getStatus());	
		
		return employee;
	}
	
	public static EmployeeDTO mapToDTO(Employee employee)
	{
		EmployeeDTO dto=new EmployeeDTO();
		dto.setId(employee.getId().longValue());
		dto.setFirstName(employee.getFirstName());
		dto.setMiddleInitial(employee.getMiddleInitial());
		dto.setLastName(employee.getLastName());
		dto.setDateOfBirth(employee.getDateOfBirth());
		dto.setDateOfEmployment(employee.getDateOfEmployment());
		//dto.setStatus(employee.getStatus());
		
		
		return dto;
	}
	
	
	public static List<Employee> mapToEmployeeList(List<EmployeeDTO> dtoList)
	{
		List<Employee> employeeList=new ArrayList<Employee>();
		for(EmployeeDTO dto:dtoList)
		{
			employeeList.add(mapToEmployee(dto));
		}
		
		
		return employeeList;
	}
}
