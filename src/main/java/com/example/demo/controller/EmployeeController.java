package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

//	@PostMapping("/save")
//	public Employee save(@Valid @RequestBody EmployeeDto emp) {
//		return service.saveEmployee(emp);
//	}

	@PostMapping("/save")
	public ResponseEntity<Employee> save(

			@RequestBody EmployeeDto employee) {

		Employee emp = service.saveEmployee(employee);

		return new ResponseEntity<>(

				emp,

				HttpStatus.CREATED

		);

	}

	@GetMapping("/getall")
	public List<EmployeeDto> getAllEmployees() {
		return service.getAll();
	}
//	
//	@GetMapping("/getbyid/{id}")
//	public EmployeeDto getById(@PathVariable int id) {
//		return service.getById(id);
//	}

	@GetMapping("/getbyidres/{id}")
	public ResponseEntity<Employee> getEmployee(

			@PathVariable int id) {

		Employee employee = service.getEmployeeById(id);

		return ResponseEntity.ok(employee);

	}

	@PutMapping("/update/{id}")
	public Employee updateEmplaoyee(@PathVariable int id, @RequestBody Employee employee) {

		return service.updateEmployee(id, employee);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(

			@PathVariable int id) {

		service.deleteEmployee(id);

		return ResponseEntity.ok(

				"Employee Deleted Successfully"

		);

	}

	@GetMapping("/employees")
	public Page<Employee> getEmployees(

			@RequestParam int page,

			@RequestParam int size) {

		return service.getEmployees(

				page,

				size);

	}

	@GetMapping("/allquery")
	public List<Employee> getAllQuery() {

		return service.getAllEmployees();

	}

	@GetMapping("/name/{name}")
	public List<Employee> getByName(

			@PathVariable String name) {

		return service.getEmployeeByName(name);

	}

	@GetMapping("/search/{name}")
	public List<Employee> searchEmployeeByName(@PathVariable String name) {

		return service.searchEmployeeByName(name);

	}
}
