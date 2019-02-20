package com.kenzan.svc;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kenzan.entities.Employee;

@Service
public interface EmployeeSvc {

	public List<Employee> find(int pageNumber, int rows, String orderBy);
	public Employee find(String id);
	public Employee create(Employee employee);
	public Employee update(Employee employee);
	public void remove(String id);
	public boolean isValidEmployee(Employee employee);
	public boolean isValidEmployeeId(String empId);
	
}
