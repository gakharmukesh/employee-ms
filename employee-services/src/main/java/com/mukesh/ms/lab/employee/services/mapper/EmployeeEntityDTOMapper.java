package com.mukesh.ms.lab.employee.services.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mukesh.ms.lab.employee.entity.EmployeeEntity;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;

public class EmployeeEntityDTOMapper 
{

	public  EmployeeEntity mapToEntity(EmployeeDTO dto)
	{
		EmployeeEntity entity=new EmployeeEntity();	
		
		if(dto==null)
		{
			return entity;
		}
		
		
		entity.setId(dto.getId());
		entity.setFirstName(dto.getFirstName());
		entity.setMiddleInitial(dto.getMiddleInitial());
		entity.setLastName(dto.getLastName());
		entity.setDateOfBirth(dto.getDateOfBirth());
		entity.setDateOfEmployment(dto.getDateOfEmployment());
		entity.setStatus(dto.getStatus());
		
		
		return entity;
	}
	
	public  EmployeeDTO mapToDTO(EmployeeEntity entity)
	{
		EmployeeDTO dto=new EmployeeDTO();
		if(entity==null)
		{
			return dto;
		}
		
		dto.setId((long)entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setMiddleInitial(entity.getMiddleInitial());
		dto.setLastName(entity.getLastName());
		dto.setDateOfBirth(entity.getDateOfBirth());
		dto.setDateOfEmployment(entity.getDateOfEmployment());
		dto.setStatus(entity.getStatus());
		
		
		return dto;
	}
	
	
	public  List<EmployeeDTO> mapDTOList(List<EmployeeEntity> entityList)
	{
		List<EmployeeDTO> dtoList=new ArrayList<>();
		
		for(EmployeeEntity entity:entityList)
		{
			
			dtoList.add(mapToDTO(entity));
		}
		
		
		return dtoList;
	}
}
