package com.mukesh.ms.lab.employee.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import com.mukesh.ms.lab.employee.entity.EmployeeEntity;
import com.mukesh.ms.lab.employee.repository.EmployeeRepository;
import com.mukesh.ms.lab.employee.repository.enums.EmployeeStatus;
import com.mukesh.ms.lab.employee.resources.models.Employee;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;
import com.mukesh.ms.lab.employee.services.interfaces.IEmployeeService;
import com.mukesh.ms.lab.employee.services.mapper.EmployeeEntityDTOMapper;


@Service
public class EmployeeServiceImpl implements IEmployeeService
{
	private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
	EmployeeRepository employeeRepository;
		
	
	@Override
	public List<EmployeeDTO> getEmployees() {
		
		System.out.println(employeeRepository.getClass());
		List<EmployeeDTO> dtoList=new ArrayList<EmployeeDTO>();
		List<EmployeeEntity> empList=employeeRepository.findByStatus(EmployeeStatus.ACTIVE.name());		
		EmployeeEntityDTOMapper.mapList(empList,dtoList);
		return dtoList;
	}
	
	

	@Override
	public EmployeeDTO addEmployee(EmployeeDTO dto) {
		// TODO Auto-generated method stub
		
		EmployeeEntity entity=EmployeeEntityDTOMapper.mapToEntity(dto);
		entity.setStatus(EmployeeStatus.ACTIVE.name());
		
		return EmployeeEntityDTOMapper.mapToDTO(employeeRepository.save(entity));
	}



	@Override
	public EmployeeDTO getEmployee(long id) {
		// TODO Auto-generated method stub
		EmployeeEntity entity=employeeRepository.findByIdAndStatus(id, EmployeeStatus.ACTIVE.name());
		if(entity==null)
		{
			throw new EntityNotFoundException();
		}
		  return EmployeeEntityDTOMapper.mapToDTO(entity);
		
	}

	@Override
	public EmployeeDTO updateEmployee(long id, EmployeeDTO dto) {
		// TODO Auto-generated method stub
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
		EmployeeEntity entity=EmployeeEntityDTOMapper.mapToEntity(dto);
		entity.setStatus(EmployeeStatus.ACTIVE.name());
		entity.setUpdate(true);
		entity=employeeRepository.save(entity);
		return EmployeeEntityDTOMapper.mapToDTO(entity);
	}

	@Override
	public EmployeeDTO deleteEmployee(long id) {
		
		EmployeeEntity entity=employeeRepository.getOne(id);
		entity.setStatus(EmployeeStatus.INACTIVE.name());
		
		return EmployeeEntityDTOMapper.mapToDTO(employeeRepository.save(entity));
	}

	
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}



	

	
	
	

}
