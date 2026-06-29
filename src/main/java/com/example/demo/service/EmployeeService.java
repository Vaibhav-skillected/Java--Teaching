package com.example.demo.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	public Employee saveEmployee(Employee employee) {
//		
//		repository.find
		
		Employee existingEmployee = repository.findByEmail(employee.getEmail());
		if(existingEmployee!= null) {
			throw new EmailAlreadyExistsException("Email Already exists");
		}
		return repository.save(employee);
	}
	
	public List<Employee> getAll(){
		return repository.findAll();
	}
	
	public Employee getById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public String deleteEmployee(int id) {
		repository.deleteById(id);
		return "data deleted on this  "+id;
	}
	
	public Employee getEmployeeById(int id) {
		return repository.findById(id)
				.orElseThrow(()-> new EmployeeNotFoundException("Employee not found"));
	}
	
	public Employee updateEmployee(int id,Employee employeeDetails) {
		
		//check emaployye exist or not 
		//if not throw exception not found 
		Employee existingEmployee = getEmployeeById(id);
		
//		existingEmployee.setName(employeeDetails.getName());
//		existingEmployee.setCity(employeeDetails.getCity());
//		existingEmployee.setSalary(employeeDetails.getSalary());
//		existingEmployee.setEmail(employeeDetails.getEmail());
		
		//user name change or not 
		if(employeeDetails.getName()!=null && !employeeDetails.getName().trim().isEmpty()) {
			existingEmployee.setName(employeeDetails.getName());
		}
		
		if(employeeDetails.getCity()!=null && !employeeDetails.getCity().trim().isEmpty()) {
			existingEmployee.setCity(employeeDetails.getCity());
			
			
		}
		
		//user provide or not
		if(employeeDetails.getEmail()!=null && !employeeDetails.getEmail().trim().isEmpty()) {
			//acually chnaging or not 
			if(!existingEmployee.getEmail().equals(employeeDetails.getEmail())){
				//does email already exist or not 
				Employee employeeWithEmail = repository.findByEmail(employeeDetails.getEmail());
				if(employeeWithEmail != null) {
					throw new EmailAlreadyExistsException("Eamail Already Exists");
				}
				
				existingEmployee.setEmail(employeeDetails.getEmail());
				
				
			}
		}
		
		return repository.save(existingEmployee);
		
	}

}
