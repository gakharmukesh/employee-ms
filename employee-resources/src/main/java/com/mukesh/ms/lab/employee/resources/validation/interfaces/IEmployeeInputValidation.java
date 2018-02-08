package com.mukesh.ms.lab.employee.resources.validation.interfaces;

import java.math.BigDecimal;

import com.mukesh.ms.lab.employee.resources.enums.OperationType;
import com.mukesh.ms.lab.employee.resources.models.Employee;
import com.mukesh.ms.lab.employee.resources.models.ValidationFailureCollection;

public interface IEmployeeInputValidation 
{
	public ValidationFailureCollection validateRequest(BigDecimal id,Employee emp,OperationType operation);
	
}
