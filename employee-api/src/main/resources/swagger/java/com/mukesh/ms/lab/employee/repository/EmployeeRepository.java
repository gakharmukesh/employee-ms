package com.mukesh.ms.lab.employee.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mukesh.ms.lab.employee.entity.EmployeeEntity;
import com.mukesh.ms.lab.employee.entity.Flight;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{

	public EmployeeEntity findByIdAndStatus(long id,String status);
	public List<EmployeeEntity> findByStatus(String status);
}
