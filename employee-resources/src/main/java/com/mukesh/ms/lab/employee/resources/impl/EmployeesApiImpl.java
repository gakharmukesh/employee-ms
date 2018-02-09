package com.mukesh.ms.lab.employee.resources.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mukesh.ms.lab.employee.resources.enums.OperationType;
import com.mukesh.ms.lab.employee.resources.interfaces.EmployeesApi;
import com.mukesh.ms.lab.employee.resources.mapper.EmployeeMapper;
import com.mukesh.ms.lab.employee.resources.models.Employee;
import com.mukesh.ms.lab.employee.resources.models.EmployeeValidation;
import com.mukesh.ms.lab.employee.resources.models.ValidationFailureCollection;
import com.mukesh.ms.lab.employee.resources.validation.interfaces.IEmployeeInputValidation;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;
import com.mukesh.ms.lab.employee.services.interfaces.IEmployeeService;

import io.swagger.annotations.ApiParam;


@Controller
@Scope(value ="prototype")
public class EmployeesApiImpl implements EmployeesApi
{
	private static final Logger logger = LoggerFactory.getLogger(EmployeesApiImpl.class);
	
	@Autowired
	IEmployeeService service;

	@Autowired
	IEmployeeInputValidation employeeInputValidation;	
	
	
	@Override
	public ResponseEntity<Object> addEmployee(@ApiParam(value = "" ,required=true ) @RequestBody Employee newEmployee,
			@ApiParam(value = "Client's conversation id"  ) @RequestHeader(value="ConversationId", required=false) String conversationId) {
		
		ValidationFailureCollection fails = employeeInputValidation.validateRequest(null,newEmployee, OperationType.CREATE);
		if (!fails.isEmpty()) {
			EmployeeValidation validation = new EmployeeValidation();
			validation.setValidation(fails);
			validation.setEmployee(newEmployee);
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(validation);
		}
		
		EmployeeDTO dto = service.addEmployee(EmployeeMapper.mapToDTO(newEmployee));
		return new ResponseEntity<Employee>(HttpStatus.CREATED).ok(EmployeeMapper.mapToEmployee(dto));
	}

	@Override
	public ResponseEntity<Object> deleteEmployeeByID(@ApiParam(value = "Employee ID",required=true ) @PathVariable("ID") BigDecimal ID,
			@ApiParam(value = "Client's conversation id"  ) @RequestHeader(value="ConversationId", required=false) String conversationId)
	{
		
		EmployeeDTO dto=service.deleteEmployee(ID.longValue());
		return new ResponseEntity<Object>(HttpStatus.OK).ok(EmployeeMapper.mapToEmployee(dto));
		
	}

	@Override
	public ResponseEntity<Object> getEmployeeByID(@ApiParam(value = "Employee ID",required=true ) @PathVariable("ID") BigDecimal ID,
			@ApiParam(value = "Client's conversation id"  ) @RequestHeader(value="ConversationId", required=false) String conversationId) 
	{
		
		Employee employee = new Employee();
		EmployeeDTO dto = service.getEmployee(ID.longValue());
	
		return new ResponseEntity<Employee>(HttpStatus.OK).ok(EmployeeMapper.mapToEmployee(dto));
	}

	@Override
	public ResponseEntity<List<Employee>> getEmployees(@ApiParam(value = "Client's conversation id"  ) @RequestHeader(value="ConversationId", required=false) String conversationId) {
		
		List<EmployeeDTO> dtoList = service.getEmployees();
		
		return new ResponseEntity<List<Employee>>(HttpStatus.OK).ok(EmployeeMapper.mapToEmployeeList(dtoList));
	}

	@Override
	public ResponseEntity<Object> updateEmployeebyID(@ApiParam(value = "Employee ID",required=true ) @PathVariable("ID") BigDecimal ID,
			@ApiParam(value = "" ,required=true ) @RequestBody Employee updateEmployee,
			@ApiParam(value = "Client's conversation id"  ) @RequestHeader(value="ConversationId", required=false) String conversationId) 
	{
		
		ValidationFailureCollection fails = employeeInputValidation.validateRequest(ID,updateEmployee,
				OperationType.CREATE);
		if (!fails.isEmpty()) {
			EmployeeValidation validation = new EmployeeValidation();
			validation.setValidation(fails);
			validation.setEmployee(updateEmployee);
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(validation);
		}

		EmployeeDTO dto = service.updateEmployee(ID.longValue(), EmployeeMapper.mapToDTO(updateEmployee));

		return new ResponseEntity<Object>(HttpStatus.OK).ok(EmployeeMapper.mapToEmployee(dto));
	}

	public void setService(IEmployeeService service) {
		this.service = service;
	}

	public void setEmployeeInputValidation(IEmployeeInputValidation employeeInputValidation) {
		this.employeeInputValidation = employeeInputValidation;
	}	
	
	


}
