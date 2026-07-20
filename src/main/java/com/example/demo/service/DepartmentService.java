package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public Department saveDepartment(
            DepartmentDto dto){

        Department department = new Department();

        department.setDepartmentName(
                dto.getDepartmentName()
        );

        return repository.save(department);

    }

}