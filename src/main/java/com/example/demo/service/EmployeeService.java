package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Address;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public Employee saveEmployee(EmployeeDto dto) {
//		
//		repository.find
		
		
		//dto-> entity 
		Employee existingEmployee = repository.findByEmail(dto.getEmail());
		if(existingEmployee!= null) {
			throw new EmailAlreadyExistsException("Email Already exists");
		}
		
		Employee employee = new Employee();
		
		employee.setName(dto.getName());
		employee.setEmail(dto.getEmail());
		employee.setMobile(dto.getMobile());
		employee.setCity(dto.getCity());
		employee.setSalary(dto.getSalary());
		//employee.setPassword(dto.getPassword());
		
		employee.setPassword(

				passwordEncoder.encode(

				dto.getPassword()

				)

				);
		employee.setRole(dto.getRole());
		//employee or admin 
		
		Address address = new Address();

		address.setHouseNo(dto.getAddress().getHouseNo());

		address.setStreet(dto.getAddress().getStreet());

		address.setCity(dto.getAddress().getCity());

		address.setState(dto.getAddress().getState());

		address.setPincode(dto.getAddress().getPincode());

		employee.setAddress(address);
		
		Department department =
		        departmentRepository.findById(
		                dto.getDepartmentId()
		        ).orElseThrow();

		employee.setDepartment(department);
		
		
		 Employee savedEmployee = repository.save(employee);

		    // Send Welcome Email
		    emailService.sendWelcomeEmail(
		            savedEmployee.getEmail(),
		            savedEmployee.getName()
		    );

		    return savedEmployee;
		
		
	}
	
//	public List<Employee> getAll(){
//		return repository.findAll();
//	}
	
	public List<EmployeeDto> getAll() {

	    List<Employee> employees = repository.findAll();

	    List<EmployeeDto> employeeDtos = new ArrayList<>();

	    for (Employee employee : employees) {

	        EmployeeDto dto = new EmployeeDto();

	        dto.setName(employee.getName());
	        dto.setEmail(employee.getEmail());
	        dto.setCity(employee.getCity());
	        dto.setMobile(employee.getMobile());
	        dto.setSalary(employee.getSalary());
	        dto.setRole(employee.getRole());

	        // Address Mapping
	        if (employee.getAddress() != null) {

	            AddressDto addressDto = new AddressDto();

	            addressDto.setHouseNo(employee.getAddress().getHouseNo());
	            addressDto.setStreet(employee.getAddress().getStreet());
	            addressDto.setCity(employee.getAddress().getCity());
	            addressDto.setState(employee.getAddress().getState());
	            addressDto.setPincode(employee.getAddress().getPincode());

	            dto.setAddress(addressDto);
	        }

	        employeeDtos.add(dto);
	    }
		return employeeDtos;
	}
	
	//entity -> dto 
	public EmployeeDto getById(int id) {
		Employee employee = repository.findById(id).orElseThrow();

		EmployeeDto dto = new EmployeeDto();

		dto.setName(employee.getName());
		dto.setEmail(employee.getEmail());
		dto.setCity(employee.getCity());
		dto.setMobile(employee.getMobile());
		dto.setSalary(employee.getSalary());
		dto.setRole(employee.getRole());

		if (employee.getAddress() != null) {

		    AddressDto addressDto = new AddressDto();

		    addressDto.setHouseNo(employee.getAddress().getHouseNo());
		    addressDto.setStreet(employee.getAddress().getStreet());
		    addressDto.setCity(employee.getAddress().getCity());
		    addressDto.setState(employee.getAddress().getState());
		    addressDto.setPincode(employee.getAddress().getPincode());

		    dto.setAddress(addressDto);
		}

		return dto;
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
	
	public Page<Employee> getEmployees(

			int page,

			int size){

			Pageable pageable=

			PageRequest.of(page,size,Sort.by("name").ascending());

			return repository.findAll(pageable);

			}
	
	
	 public List<Employee> getAllEmployees(){

	        return repository.getAllEmployees();

	    }
	 
	 public List<Employee> getEmployeeByName(String name){

		    return repository.getEmployeeByName(name);

		}
	 
	 public List<Employee> searchEmployeeByName(String name) {

	        return repository.searchByName(name);

	    }

	 
}
