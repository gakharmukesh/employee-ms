package com.mukesh.ms.lab.employee.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;

public class EmployeeProcessor implements ItemProcessor<EmployeeDTO,EmployeeDTO>
{

	private static final Logger log = LoggerFactory.getLogger(EmployeeProcessor.class);
	
	@Override
	public EmployeeDTO process(EmployeeDTO item) throws Exception {
		
		return item;
	}
	
	

}