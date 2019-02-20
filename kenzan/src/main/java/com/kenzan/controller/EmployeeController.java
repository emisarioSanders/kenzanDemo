package com.kenzan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kenzan.config.SecurityConfig;
import com.kenzan.entities.Employee;
import com.kenzan.svc.EmployeeSvc;

import io.micrometer.core.instrument.util.StringUtils;
/**
 * 
 * @author Luis Arenas
 * Controller for all resource api operations.
 * implements service layer design patter in order to separate the entry points of the application.
 *
 */
@RestController
public class EmployeeController {

	@Autowired
	EmployeeSvc empSvc;

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		boolean isValid = empSvc.isValidEmployee(employee);
		ResponseEntity<Employee> response = null;
		Employee resultEmployee = null;
		if (!isValid) {
			response = new ResponseEntity<Employee>(employee, HttpStatus.BAD_REQUEST);
		} else {
			resultEmployee = empSvc.create(employee);
			return new ResponseEntity<Employee>(resultEmployee,HttpStatus.CREATED);
		}

		return response;

	}

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public List<Employee> find(@RequestParam(value = "pageNumber", defaultValue = "0") Integer pageNumber,
			@RequestParam(value = "rows", defaultValue = "10") Integer rows,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
		return empSvc.find(pageNumber, rows, orderBy);
	}
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> find(@PathVariable(value = "id") String id) {
		ResponseEntity<Employee> response = null;
		if (StringUtils.isEmpty(id))
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);

		Employee employee = empSvc.find(id);

		if (employee != null)
			response = new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
		else
			response = new ResponseEntity<Employee>(employee, HttpStatus.NOT_FOUND);

		return response;
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") String id,
			@RequestBody Employee employee) {
		ResponseEntity<Employee> response = null;
		if(!empSvc.isValidEmployeeId(id)){
			return new ResponseEntity<Employee>(employee, HttpStatus.BAD_REQUEST);
		}
		employee.setId(id);

		boolean isValid = empSvc.isValidEmployee(employee);
		if (!isValid) {
			response = new ResponseEntity<Employee>(employee, HttpStatus.BAD_REQUEST);
		} else {
			empSvc.update(employee);
			return new ResponseEntity<Employee>(HttpStatus.OK);
		}
		return response;
	}

	@Autowired
	SecurityConfig securityConfig;
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> removeEmployee(@PathVariable(value = "id") String id,
			@RequestHeader("authorization") String authString) {
		ResponseEntity<Employee> response = null;
		if (securityConfig.isAuthorized(authString)) {
			Employee emp =empSvc.find(id);
			if(emp == null)
				response  =new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
			else {
				empSvc.remove(id);
				response= new ResponseEntity<Employee>(HttpStatus.OK);
			}
		} else {
			response = new ResponseEntity<Employee>(HttpStatus.UNAUTHORIZED);
		}
		return response;
	}
}
