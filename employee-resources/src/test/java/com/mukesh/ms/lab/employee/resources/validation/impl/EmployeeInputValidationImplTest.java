package com.mukesh.ms.lab.employee.resources.validation.impl;

import java.math.BigDecimal;


import org.junit.Before;
import org.junit.Test;

import com.mukesh.ms.lab.employee.resources.enums.OperationType;
import com.mukesh.ms.lab.employee.resources.models.Employee;
import com.mukesh.ms.lab.employee.resources.models.ValidationFailure;
import com.mukesh.ms.lab.employee.resources.models.ValidationFailureCollection;
import com.mukesh.ms.lab.employee.resources.validation.interfaces.IEmployeeInputValidation;


import junit.framework.TestCase;

public class EmployeeInputValidationImplTest extends TestCase
{
	
	private static IEmployeeInputValidation validator;
	private static Employee emp;
	private static BigDecimal bigDecimal;
	
	
	@Before
	public void setUp()
	{
		validator=new EmployeeInputValidationImpl();
		bigDecimal=new BigDecimal(101);
		emp=new Employee();
		emp.setMiddleInitial("KK");
		emp.setStatus("ACTIVE");;
		
	}

	
	@Test
	public void testValidateRequest()
	{
		
		
		//Employee input with status value
		ValidationFailureCollection failureCollection=validator.validateRequest(bigDecimal, emp, OperationType.CREATE);
		assertNotNull(failureCollection);
		
		assertTrue(failureCollection.size()==7);
		
		//Employee input with null first name
		ValidationFailure fail=failureCollection.get(0);
		assertEquals("EmployeeInputValidation.badAtrVal[Employee.id=*null*]. EmployeeInputValidation.notNull.Value can't be null or empty.", fail.getMessage());
				
	   
		//Employee input with null first name
		 fail=failureCollection.get(1);
		assertEquals("EmployeeInputValidation.badAtrVal[Employee.firstName=*null*]. EmployeeInputValidation.notNull.Value can't be null or empty.", fail.getMessage());
	
		
	    //Employee input with lastname null
		fail=failureCollection.get(2);
		assertEquals("EmployeeInputValidation.badAtrVal[Employee.lastName=*null*]. EmployeeInputValidation.notNull.Value can't be null or empty.", fail.getMessage());
		
		//Employee input with dateofBirth null
		fail=failureCollection.get(3);
		assertEquals("EmployeeInputValidation.badAtrVal[Employee.dateOfBirth=*null*]. EmployeeInputValidation.notNull.Value can't be null or empty.", fail.getMessage());
		
		//Employee input with dateOfEmployment null
		fail=failureCollection.get(4);
		assertEquals("EmployeeInputValidation.badAtrVal[Employee.dateOfEmployment=*null*]. EmployeeInputValidation.notNull.Value can't be null or empty.", fail.getMessage());
		
		//Employee input with status
		fail=failureCollection.get(5);
		assertEquals("EmployeeInputValidation.badAtrVal[Employee.status=ACTIVE]. EmployeeInputValidation.NotRequired.Please remove it from input.", fail.getMessage());
		
		//Employee input with KK middle initial
		fail=failureCollection.get(6);
		assertEquals("EmployeeInputValidation.badAtrVal[Employee.middleInitial=KK]. EmployeeInputValidation.maxLength allowed is 1", fail.getMessage());
	
				
		
		
		
		
		
	
		
		
		
	}
}
