package com.kenzan.svc;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.kenzan.config.ApplicationProperties;
import com.kenzan.entities.Employee;
import com.kenzan.repository.EmployeeRepository;

/**
 * 
 * @author Luis Arenas this class handle the business logic of the application.
 *         here we can find data management with streams and lambda functions.
 *         invertion of Control is achieved by spring Autowired anotations.
 */
@Service
public class EmployeeSvcImpl implements EmployeeSvc {

	@Autowired
	EmployeeRepository empRepo;

	@Autowired
	ApplicationProperties applicationProperties;

	@Override
	public List<Employee> find(int pageNumber, int row, String orderBy) {
		List<Employee> employees = empRepo.find();
		List<Employee> resultList = sorteList(employees, orderBy, pageNumber, row);
		return resultList;

	}

	@Override
	public Employee find(String id) {
		Employee emp = empRepo.find(id);
		if (emp != null)
			return emp;
		else
			return null;
	}

	@Override
	public Employee create(Employee employee) {
		return empRepo.createEmployee(employee);

	}

	@Override
	public Employee update(Employee employee) {
		return empRepo.update(employee);
	}

	@Override
	public void remove(String id) {
		empRepo.delete(id);
	}

	public List<Employee> sorteList(List<Employee> inputList, String sortBy, int pageNumber, int rows) {
		Function<Employee, String> sortByFunction = operation(sortBy);
		List<Employee> resultList = inputList.stream().sorted(Comparator.comparing(e -> sortByFunction.apply(e)))
				.filter(e -> e.isActive()).skip(rows * pageNumber).limit(rows).collect(Collectors.toList());
		return resultList;
	}

	public Function<Employee, String> operation(String ex) {
		if (!StringUtils.isEmpty(ex)) {
			if (ex.equals(applicationProperties.getId()))
				return (e) -> e.getId();
			else if (ex.equals(applicationProperties.getFirstName()))
				return (e) -> e.getFirstName();
			else if (ex.equals(applicationProperties.getLastName()))
				return (e) -> e.getLastName();
			else if (ex.equals(applicationProperties.getDateOfBirth()))
				return (e) -> e.getDateOfBirth();
			else if (ex.equals(applicationProperties.getLastName()))
				return (e) -> e.getDateOfEmployment();
		}

		return (e) -> e.getId();
	}

	@Override
	public boolean isValidEmployee(Employee employee) {
		if (employee == null || StringUtils.isEmpty(employee.getFirstName())
				|| StringUtils.isEmpty(employee.getLastName()) || StringUtils.isEmpty(employee.getDateOfBirth())
				|| StringUtils.isEmpty(employee.getDateOfEmployment()) || employee.isActive() == false) {
			return false;
		}
		return true;
	}

	public boolean isValidEmployeeId(String empId) {
		return ObjectId.isValid(empId);
	}

}
