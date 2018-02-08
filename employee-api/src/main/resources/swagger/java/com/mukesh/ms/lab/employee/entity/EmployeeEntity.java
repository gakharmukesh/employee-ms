package com.mukesh.ms.lab.employee.entity;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.domain.Persistable;




@Entity
public class EmployeeEntity implements Persistable 
{
	  @Id
	  private long id;
	  
	  private String firstName = null;
	  private String middleInitial = null;
	  private String lastName = null;
	  private LocalDate dateOfBirth = null;
	  private LocalDate dateOfEmployment = null;
	  private String status;
	  
	  public  EmployeeEntity()
	  {
		super();  
	  }

	  @Transient
	    private boolean update; 
	
	  
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
		// TODO Auto-generated method stub
		return id;
	}
	
	 public void setUpdate(boolean update) {
	        this.update = update;
	    }

	@Override
	public boolean isNew() {
		// TODO Auto-generated method stub
		return !this.update;
	}

	  

}
