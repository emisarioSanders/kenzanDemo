package com.kenzan.entities;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 
 * @author Luis Arenas
 * this class implements builder design patter in order to build the employe object.
 *
 */
@Document(collection = "employees")
public class Employee {

	@Id
	private String Id = null;

	@JsonProperty("firstName")
	private String firstName = null;

	@JsonProperty("middleInitial")
	private String middleInitial = null;

	@JsonProperty("lastName")
	private String lastName = null;

	@JsonProperty("dateOfBirth")
	private String dateOfBirth = null;

	@JsonProperty("dateOfEmployment")
	private String dateOfEmployment = null;

	@JsonProperty("active")
	private Boolean active = null;

	public Employee firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Employee middleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
		return this;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public Employee lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Employee dateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Employee dateOfEmployment(String dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
		return this;
	}

	public String getDateOfEmployment() {
		return dateOfEmployment;
	}

	public void setDateOfEmployment(String dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}

	public Employee active(Boolean active) {
		this.active = active;
		return this;
	}

	public Boolean isActive() {
		return active;
	}

	public void setIsActive(Boolean status) {
		this.active = status;
	}

	public String getId() {
		return Id;
	}

	public void setId(String iD) {
		Id = iD;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Employee employee = (Employee) o;
		return Objects.equals(this.Id, employee.Id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, middleInitial, lastName, dateOfBirth, dateOfEmployment, active);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");

		sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
		sb.append("    middleInitial: ").append(toIndentedString(middleInitial)).append("\n");
		sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
		sb.append("    dateOfBirth: ").append(toIndentedString(dateOfBirth)).append("\n");
		sb.append("    dateOfEmployment: ").append(toIndentedString(dateOfEmployment)).append("\n");
		sb.append("    status: ").append(toIndentedString(active)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
