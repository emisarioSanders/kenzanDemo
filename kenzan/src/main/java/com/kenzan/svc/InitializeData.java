package com.kenzan.svc;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.kenzan.config.ApplicationProperties;
import com.kenzan.entities.Employee;
import com.kenzan.repository.EmployeeRepository;

@Component
public class InitializeData {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	public void loadDataAfterStartup() {	
		InputStream input = null;
		try {
			input = new ClassPathResource(applicationProperties.getDataFile()).getInputStream();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		CsvSchema csvSchema = CsvSchema.builder().setUseHeader(true).build();
		CsvMapper csvMapper = new CsvMapper();

		List<Object> readAll = null;
		try {
			readAll = csvMapper.readerFor(Employee.class).with(csvSchema).readValues(input).readAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Object o : readAll) {
			if (o != null) {
				try {
					employeeRepository.createEmployee((Employee) o);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
