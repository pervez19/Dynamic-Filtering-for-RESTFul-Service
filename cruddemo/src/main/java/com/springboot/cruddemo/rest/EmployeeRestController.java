package com.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springboot.cruddemo.entity.Employee;
import com.springboot.cruddemo.service.EmployeeServiceImp;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeServiceImp employeeService;

	@Autowired
	public EmployeeRestController(EmployeeServiceImp employeeServiceImp) {
		this.employeeService = employeeServiceImp;
	}
	
	@GetMapping("/employees")
	public MappingJacksonValue findAll()
	{
		List<Employee> employees = employeeService.findAll();
		MappingJacksonValue mapping=new MappingJacksonValue(employees);
		
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.  
				filterOutAllExcept("firstName","lastName");
		
		FilterProvider filters=new SimpleFilterProvider().
				addFilter("EmployeeFilter", filter);
		
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	@GetMapping("/employees/{id}")
	public Employee findById(@PathVariable int id)
	{
		Employee employee=employeeService.findById(id);
		if(employee==null)
		{
			throw new RuntimeException("Employee not found by this ID: "+id);
		}
		return employee;
		
	}
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee)
	{
		employee.setId(0);
		employeeService.save(employee);
		return employee;
		
	}
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee employee)
	{
		employeeService.save(employee);
		return employee;
		
	}
	
	@DeleteMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable int id)
	{
		Employee employee=employeeService.findById(id);
		if(employee==null)
		{
			throw new RuntimeException("Employee not found by this ID"+id);
		}
		
		employeeService.deleteById(id);
		
		return "Deleted Employee id: "+id;
	}
	
	
	
	
	
	
	
}
