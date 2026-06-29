package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	@GetMapping("/all")
	public String allTeachers() {
		return "All teachers";
		
	}
	
	@GetMapping("/{id}")
	public String getTeacher(@PathVariable int id) {
		
		return "Taecher Id " + id; 
	}

	@GetMapping("/{id}/{name}")
	public String getTeacherDetails(@PathVariable int id,
			@PathVariable String name) {
		
		return "Taecher Id " + id +" and Teacher name is " + name; 
		
		
	}
	
	
	@GetMapping("/search")
	public String serach(@RequestParam String name) {
		System.out.println("search function run....");
		return "name = " + name;
	}
	
	//browser/postman -> /search?name=skillected -> @requestParam-> return respnse
	
	@GetMapping("/searchbynameandlastname")
	public String searchByNameAndLastName(@RequestParam String firstName,
			@RequestParam String lastName) {
		return "First name = "+ firstName +" LastName = "+ lastName;
	}
	
	@PostMapping("/save")
	public String saveTeacher() {
		return "Teacher savecd";
	}
	
	//@PutMapping
	
	
	
	
	
	
}
