package com.mukesh.ms.lab.employee.repository;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mukesh.ms.lab.employee.entity.EmployeeEntity;


@Repository
public  interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long>
{
	public List<EmployeeEntity> findByStatus(String status);
	public  EmployeeEntity findByIdAndStatus(long id,String status);
}
