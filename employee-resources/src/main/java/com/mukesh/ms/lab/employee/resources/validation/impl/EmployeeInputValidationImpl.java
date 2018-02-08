package com.mukesh.ms.lab.employee.resources.validation.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mukesh.ms.lab.employee.resources.models.ValidationFailure;
import com.mukesh.ms.lab.employee.resources.enums.OperationType;
import com.mukesh.ms.lab.employee.resources.models.Employee;
import com.mukesh.ms.lab.employee.resources.models.ValidationFailureCollection;
import com.mukesh.ms.lab.employee.resources.validation.interfaces.IEmployeeInputValidation;

public class EmployeeInputValidationImpl implements IEmployeeInputValidation {

	ValidationFailureCollection fails = new ValidationFailureCollection();
	private static final String BADATR_MSG_ID = "EmployeeInputValidation.badAtrVal";
	private static final String DELIM = ".";

	@Override
	public ValidationFailureCollection validateRequest(BigDecimal id,Employee emp, OperationType operation) {
		// TODO Auto-generated method stub
		List<String> objPath = new ArrayList<>();
		objPath.add("Employee");
		
		
		
		requireNonNull(objPath,"id",emp.getId(),"EMP00100");
		requireNonNull(objPath,"firstName",emp.getFirstName(),"EMP00100");
		requireNonNull(objPath,"lastName",emp.getLastName(),"EMP00100");
		requireNonNull(objPath,"dateOfBirth",emp.getDateOfBirth(),"EMP00100");
		requireNonNull(objPath,"dateOfEmployment",emp.getDateOfEmployment(),"EMP00100");
		NotRequired(objPath,"status",emp.getStatus(),"EMP00200");
		
		requireMaxLength(objPath,"middleInitial",emp.getMiddleInitial(),1,"EMP00300");
		
		NotEqual(objPath,"id",id,emp.getId(),"EMP00100");
		
		return fails;
	}

	private void requireNull(List<String> objPath, String atrName, Object val, String code) {
		if (val != null) {
			addAction(failAtrVal(BADATR_MSG_ID, objPath, atrName, val, code), "EmployeeInputValidation.requireNull");
		}
	}
	
	private void NotEqual(List<String> objPath, String atrName, BigDecimal source,BigDecimal target, String code) {
		if (source != null && target!=null && source.compareTo(target)!=0) {
			addAction(failAtrVal(BADATR_MSG_ID, objPath, atrName, target, code), "EmployeeInputValidation.NotEqual.Can't be changed and expected value is "+source);
		}
	}
	
	private void NotEqual(List<String> objPath, String atrName, Object source,Object target, String code) {
		if (source != null && target!=null && source!=target) {
			addAction(failAtrVal(BADATR_MSG_ID, objPath, atrName, target, code), "EmployeeInputValidation.NotEqual.Can't be changed and expected value "+source);
		}
	}
	private void NotRequired(List<String> objPath, String atrName, String val, String code) {
		if (val != null) {
			addAction(failAtrVal(BADATR_MSG_ID, objPath, atrName, val, code), "EmployeeInputValidation.NotRequired.Please remove it from input.");
		}
	}

	private boolean requireNonNull(List<String> objPath, String atrName, Object val, String code) {
		if (val == null ) {
			addAction(failAtrVal(BADATR_MSG_ID, objPath, atrName, val, code), "EmployeeInputValidation.notNull.Value can't be null or empty.");
			return false;
		}
		return true;
	}
	
	private boolean requireNonNull(List<String> objPath, String atrName, String val, String code) {
		if (val == null || val.trim().length()==0) {
			addAction(failAtrVal(BADATR_MSG_ID, objPath, atrName, val, code), "EmployeeInputValidation.notNull.Value can't be null or empty.");
			return false;
		}
		return true;
	}
	
	private boolean requireMaxLength(List<String> objPath, String atrName, String val,int length,String code) {
		if (val!=null&& val.length()>1) {
			addAction(failAtrVal(BADATR_MSG_ID, objPath, atrName, val, code), "EmployeeInputValidation.maxLength allowed is "+length);
			return false;
		}
		return true;
	}

	private ValidationFailure failAtrVal(String msgId, List<String> objPath, String atrName, Object val, String code,
			String... inMsgArgs) {
		List<String> msgArgs = new ArrayList<>(inMsgArgs.length + 1);
		msgArgs.add(makeObjPathStr(objPath) + DELIM + atrName + "=" + (val == null ? "*null*" : val)); //$NON-NLS-1$
		msgArgs.addAll(Arrays.asList(inMsgArgs));
		return fail(msgId+msgArgs, code);
	}

	private String makeObjPathStr(List<String> objPath) {
		StringBuilder buf = new StringBuilder();
		boolean isFirst = true;
		for (String pathElem : objPath) {
			if (pathElem.charAt(0) != '[' && !isFirst) { // Elem isn't [n] or first
				buf.append(DELIM);
			}
			isFirst = false;
			buf.append(pathElem);
		}
		return buf.toString();
	}

	private ValidationFailure fail(String msg, String code) {
		ValidationFailure fail = new ValidationFailure().message(msg);
		fail.setCode(code);
		fail.setSeverity("ERROR");
		fails.add(fail);
		return fail;
	}

	

	private void addAction(ValidationFailure ve, String action) {
		if (ve == null || action == null) {
			return;
		}
		if (ve.getMessage() == null) {
			ve.setMessage(action);
		}
		ve.setMessage(ve.getMessage() + ". " + action);
	}

}
