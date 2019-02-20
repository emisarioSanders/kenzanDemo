package com.kenzan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

	@Value("${auth.user}")
	private String user;

	@Value("${auth.password}")
	private String password;

	@Value("${auth.type}")
	private String type;

	@Value("${pagination.filterby.id}")
	private String id;

	@Value("${pagination.filterby.firstName}")
	private String firstName;

	@Value("${data.dataFile}")
	private String dataFile;
	
	public String getDataFile() {
		return dataFile;
	}

	public void setDataFile(String dataFile) {
		this.dataFile = dataFile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfEmployment() {
		return dateOfEmployment;
	}

	public void setDateOfEmployment(String dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}

	@Value("${pagination.filterby.lastName}")
	private String lastName;

	@Value("${pagination.filterby.dateOfBirth}")
	private String dateOfBirth;

	@Value("${pagination.filterby.dateOfEmployment}")
	private String dateOfEmployment;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
