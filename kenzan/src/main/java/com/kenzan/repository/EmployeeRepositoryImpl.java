package com.kenzan.repository;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.kenzan.entities.Employee;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
/**
 * 
 * @author Luis Arenas
 * this class is the persistance layer of the application.
 * the database is a No relational (No Sql) Mongo db.
 *
 */
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	public MongoTemplate mongo;

	@Override
	public Employee createEmployee(Employee employee) {
		return mongo.save(employee);
	}

	@Override
	public void delete(String id) {
		Employee emp = find(id);
		if(emp != null) {
			emp.setIsActive(false);
			update(emp);
		}
	}

	@Override
	public Employee update(Employee employee) {
		MongoCollection<Document> employees = mongo.getCollection("employees");
		Document newPerson = new Document();
		newPerson.append("firstName", employee.getFirstName())
				.append("middleInitial", employee.getMiddleInitial())
				.append("lastName", employee.getLastName())
				.append("dateOfBirth", employee.getDateOfBirth())
				.append("dateOfEmployment", employee.getDateOfEmployment())
				.append("active", employee.isActive());

		employees.replaceOne(Filters.eq("_id", new ObjectId(employee.getId())), newPerson);
		
		return find(employee.getId());

	}

	@Override
	public Employee find(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id).and("active").is(true));
		List<Employee> employees = mongo.find(query, Employee.class);
		if (employees != null && employees.size() >= 1)
			return employees.get(0);
		else
			return null;
	}

	@Override
	public List<Employee> find() {
		Query query = new Query();
		query.addCriteria(Criteria.where("active").is(true));
		List<Employee> employees = mongo.find(query, Employee.class);
		return employees;
	}

}
