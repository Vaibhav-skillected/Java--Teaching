package com.example.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;

@RestController
//@CrossOrigin("localhost")
public class HomeController {

	@GetMapping("/home")
	public String hello() {
		return "Welcome Students";
	}
	
	@GetMapping("/student")
	public Student getStudent() {
		
		return new Student(1,"Skillected");
	}
	
	@PostMapping("/savedata")
	public Student savestudent(@RequestBody Student student){
		
		return student;
		
	}
	
	
	//curd -> create update read delete  
	//patch 
}

//browser -> localhost:8081/hello -> springboot -> homecontroller -> hello() -> return string 