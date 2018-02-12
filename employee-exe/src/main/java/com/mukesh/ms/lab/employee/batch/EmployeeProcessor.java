package com.mukesh.ms.lab.employee.batch;


import org.springframework.batch.item.ItemProcessor;


import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;

public class EmployeeProcessor implements ItemProcessor<EmployeeDTO,EmployeeDTO>
{

	
	
	@Override
	public EmployeeDTO process(EmployeeDTO item) throws Exception {
		
		return item;
	}
	
	

}