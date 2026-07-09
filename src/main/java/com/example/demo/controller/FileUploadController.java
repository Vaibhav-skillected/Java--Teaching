package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.FileUploadService;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileUploadService service;
    
    @PostMapping("/upload")
    public String uploadFile(

    @RequestParam("file") MultipartFile file)

    throws Exception{

        return service.uploadFile(file);

    }

}