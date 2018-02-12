package com.mukesh.ms.lab.employee.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;

@Entity
public class EmployeeEntity implements Persistable
{
	@Id
	private long id;
	private String firstName;
	private String middleInitial;
	private String lastName;
	private LocalDate dateOfBirth;
	private LocalDate dateOfEmployment;
	private String status;
	@Transient
    private boolean update;

	
    public EmployeeEntity()
    {
    	super();
    }
	
	public EmployeeEntity(long id, String firstName, String middleInitial, String lastName, LocalDate dateOfBirth,
			LocalDate dateOfEmployment, String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfEmployment = dateOfEmployment;
		this.status = status;
		this.update = update;
	}
	
	

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateOfEmployment() {
		return dateOfEmployment;
	}

	public void setDateOfEmployment(LocalDate dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public Serializable getId() {
		
		return id;
	}

	@Override
	public boolean isNew() {
		
		return !update;
	}

}
