package com.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDaoImp implements EmployeeDao {

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDaoImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		//get Current Session
		Session currentSession= entityManager.unwrap(Session.class);
		
		//Create a Query
		Query<Employee> query=currentSession
								.createQuery("from Employee",Employee.class);
		
		//execute query and get result list
		List<Employee> employees=query.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int id) {
		
		Session currentSession= entityManager.unwrap(Session.class);
		Employee employee=currentSession.get(Employee.class, id);
		return employee;
	}

	@Override
	public void save(Employee employee) {
		Session currentSession= entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(employee);
	}

	@Override
	public void deleteById(int id) {
		Session currentSession= entityManager.unwrap(Session.class);
		Query query=currentSession
				.createQuery("delete from Employee where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
