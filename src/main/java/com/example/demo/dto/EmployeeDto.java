package com.example.demo.dto;

import com.example.demo.enums.Role;

public class EmployeeDto {

	private String name;
	
	private String email;
	
	private String city;
	
	private String mobile;
	private double salary;
	private String password;
	
	private Role role;
	private AddressDto address;
	
	private Integer departmentId;

	
	
	public EmployeeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeDto(String name, String email, String city, String mobile) {
		super();
		this.name = name;
		this.email = email;
		this.city = city;
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	public AddressDto getAddress() {
	    return address;
	}

	public void setAddress(AddressDto address) {
	    this.address = address;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	
}
