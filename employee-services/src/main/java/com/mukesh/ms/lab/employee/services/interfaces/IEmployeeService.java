package com.mukesh.ms.lab.employee.services.interfaces;


import java.util.List;


import com.mukesh.ms.lab.employee.services.dto.EmployeeDTO;

public interface IEmployeeService 
{

	List<EmployeeDTO> getEmployees();
	EmployeeDTO addEmployee(EmployeeDTO dto);
	EmployeeDTO getEmployee(long id);
	EmployeeDTO updateEmployee(long id,EmployeeDTO employee);
	EmployeeDTO deleteEmployee(long id);
}
