package com.mukesh.ms.lab.employee.batch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.mukesh.ms.lab.employee.entity.EmployeeEntity;
import com.mukesh.ms.lab.employee.repository.EmployeeRepository;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;
import com.mukesh.ms.lab.employee.services.mapper.EmployeeEntityDTOMapper;

public class EmployeeItemWriter implements ItemWriter<EmployeeDTO>
{
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeItemWriter.class);

	@Autowired
	EmployeeRepository repository;
	
	@Autowired
	EmployeeEntityDTOMapper mapper;
	
	@Override
	public void write(List<? extends EmployeeDTO> items) {
		
		
		for(EmployeeDTO dto:items)
		{
			try
			{
				EmployeeEntity entity=mapper.mapToEntity(dto);
				entity.setStatus(dto.getStatus());
				repository.save(entity);
			}catch(Exception e)
			{
				log.error("EmployeeItemWriter::write() failed for item"+dto.getId()+"  "+ e.getMessage());
				log.error("EmployeeItemWriter::write() Exception",e);
			}
		}
		
	}

	

}
