package com.kenzan.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kenzan.entities.Employee;

@Repository
public interface EmployeeRepository {
	
	public Employee createEmployee(Employee employee);
	
	public void delete(String id);


	public Employee update(Employee employee);


	public Employee find(String id);


	public List<Employee> find();

}
