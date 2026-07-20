package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @PostMapping("/save")
    public Department save(
            @RequestBody DepartmentDto dto){

        return service.saveDepartment(dto);

    }

}