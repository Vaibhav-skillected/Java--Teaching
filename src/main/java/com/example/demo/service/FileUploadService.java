package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file)
            throws IOException {

        Path path = Paths.get(uploadDir);

        if (!Files.exists(path)) {

            Files.createDirectories(path);

        }

        Path filePath = path.resolve(file.getOriginalFilename());

        file.transferTo(filePath);

        return "File Uploaded Successfully";

    }
}