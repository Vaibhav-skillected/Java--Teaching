package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping("/save")
	public Employee save(@RequestBody Employee emp) {
		return service.saveEmployee(emp);
	}
	
	@GetMapping("/getall")
	public List<Employee> getAllEmployees(){
		return service.getAll();
	}
	
	@PutMapping("/update/{id}")
	public Employee updateEmplaoyee(@PathVariable int id, @RequestBody Employee employee ) {
		
		return service.updateEmployee(id, employee);
		
	}

}
