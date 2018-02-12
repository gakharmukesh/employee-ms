package com.mukesh.ms.lab.employee.batch.mapper;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.validation.BindException;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;



public class EmployeeFieldSetMapper implements FieldSetMapper<EmployeeDTO> {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeFieldSetMapper.class);
	
	@Value("${spring.jackson.date-format}")
	private String pattern;
	
	
	private DateTimeFormatter formatter;
	
	public EmployeeFieldSetMapper()
	{
		super();
	}
	

	public DateTimeFormatter getFormatter()
	{
		return formatter;
	}

	public void setFormatter(DateTimeFormatter formatter) {
		this.formatter = formatter;
	}

	@PostConstruct
	public void init()
	{
		
		setFormatter(DateTimeFormatter.ofPattern(pattern) );
	}
	
	@Override
	public EmployeeDTO mapFieldSet(FieldSet arg0) throws BindException {
		
		
		EmployeeDTO dto=null;
		try
		{
			
			dto=new EmployeeDTO();		
			dto.setId(arg0.readLong(0));
			dto.setFirstName(arg0.readString(1));
			dto.setMiddleInitial(arg0.readString(2));
			dto.setLastName(arg0.readString(3));
			dto.setDateOfBirth(LocalDate.parse(arg0.readString(4), getFormatter()));
			dto.setDateOfEmployment(LocalDate.parse(arg0.readString(5), formatter));
			dto.setStatus(arg0.readString(6));
		}catch (Exception e) {
			log.error("EmployeeFieldSetMapper::mapFieldSet() CSVFile Failed for ID:"+arg0.readLong(0));
			log.error("Error:",e);
		}	
		
		
		return dto;
	}


}
