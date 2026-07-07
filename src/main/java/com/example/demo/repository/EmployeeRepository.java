package com.example.demo.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	//save()
	//findAll()
	//findBgyId()
	//deleteById()
	
	Employee findByEmail(String email);
	Employee findByName(String name);
	
	//Employee findBySalaryGreaterThanAndEmailAndNameAndCityStratingWithOrderBySalaryDes();
	//
	
	
	@Query("SELECT e FROM Employee e")
	
	//select * from employees;

    List<Employee> getAllEmployees();
	
	//controller-> service-> repoository -> jpql -> hibernate ->sql ->database
	//hibernate is a transelator between jpql and sql 
	
	
	
	 @Query("SELECT e FROM Employee e WHERE e.name=:name")
	 
	 //select * from employees e where e.name=:name;
	 List<Employee> getEmployeeByName(@Param("name") String name);
	 
	 @Query("SELECT e FROM Employee e WHERE e.city=:city")
	 List<Employee> getEmployeeByCity(@Param("city") String city

	 );
	 
	 @Query("""

			 SELECT e

			 FROM Employee e

			 WHERE e.name LIKE %:name%

			 """)

			 List<Employee> searchByName(

			 @Param("name") String name

			 );
	

}
