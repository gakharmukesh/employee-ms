package com.mukesh.ms.lab.employee.services.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.mukesh.ms.lab.employee.resources.models.Employee;
import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;

public interface IEmployeeService 
{

	public List<EmployeeDTO> getEmployees();
	public EmployeeDTO addEmployee(EmployeeDTO dto);
	public EmployeeDTO getEmployee(long id);
	public EmployeeDTO updateEmployee(long id,EmployeeDTO employee);
	public EmployeeDTO deleteEmployee(long id);
}
