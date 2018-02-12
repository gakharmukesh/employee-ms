package com.mukesh.ms.lab.employee.services.impl;


import java.util.List;

import javax.persistence.EntityNotFoundException;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.mukesh.ms.lab.employee.entity.EmployeeEntity;
import com.mukesh.ms.lab.employee.repository.EmployeeRepository;
import com.mukesh.ms.lab.employee.repository.enums.EmployeeStatus;

import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;
import com.mukesh.ms.lab.employee.services.interfaces.IEmployeeService;
import com.mukesh.ms.lab.employee.services.mapper.EmployeeEntityDTOMapper;


@Service
public class EmployeeServiceImpl implements IEmployeeService
{
	
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeEntityDTOMapper mapper;
		
	
	@Override
	public List<EmployeeDTO> getEmployees() {
		
		
		
		List<EmployeeEntity> empList=employeeRepository.findByStatus(EmployeeStatus.ACTIVE.name());			
		return mapper.mapDTOList(empList);
	}
	
	

	@Override
	public EmployeeDTO addEmployee(EmployeeDTO dto) {
		
		
		EmployeeEntity entity=mapper.mapToEntity(dto);
		entity.setStatus(EmployeeStatus.ACTIVE.name());
		
		return mapper.mapToDTO(employeeRepository.save(entity));
	}



	@Override
	public EmployeeDTO getEmployee(long id) {
		
		EmployeeEntity entity=employeeRepository.findByIdAndStatus(id, EmployeeStatus.ACTIVE.name());
		if(entity==null)
		{
			throw new EntityNotFoundException();
		}
		  return mapper.mapToDTO(entity);
		
	}

	@Override
	public EmployeeDTO updateEmployee(long id, EmployeeDTO dto) {
		
		/*EmployeeEntity entity=employeeRepository.getOne(id);
		if(entity!=null)
		{
			entity.setFirstName(dto.getFirstName());
			entity.setMiddleInitial(dto.getMiddleInitial());
			entity.setLastName(dto.getLastName());
			entity.setDateOfBirth(dto.getDateOfBirth());
			entity.setDateOfEmployment(dto.getDateOfEmployment());
		}else
		{
			entity=EmployeeEntityDTOMapper.mapToEntity(dto);
		}*/
		EmployeeEntity entity=mapper.mapToEntity(dto);
		entity.setStatus(EmployeeStatus.ACTIVE.name());
		entity.setUpdate(true);
		entity=employeeRepository.save(entity);
		return mapper.mapToDTO(entity);
	}

	@Override
	public EmployeeDTO deleteEmployee(long id) {
		
		EmployeeEntity entity=employeeRepository.getOne(id);
		entity.setStatus(EmployeeStatus.INACTIVE.name());
		
		return mapper.mapToDTO(employeeRepository.save(entity));
	}

	
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}



	public void setMapper(EmployeeEntityDTOMapper mapper) {
		this.mapper = mapper;
	}



	

	
	
	

}
