package com.example.demo.corsConfig;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DotEnvLoader {

    public static void load() {

        Path path = Paths.get(".env");

        if (!Files.exists(path)) {
            System.out.println(".env file not found.");
            return;
        }

        try {

            Files.lines(path)
                    .filter(line -> !line.trim().isEmpty())
                    .filter(line -> !line.startsWith("#"))
                    .forEach(line -> {

                        String[] parts = line.split("=", 2);

                        if (parts.length == 2) {
                            System.setProperty(
                                    parts[0].trim(),
                                    parts[1].trim()
                            );
                        }

                    });

            System.out.println(".env loaded successfully.");

        } catch (IOException e) {

            throw new RuntimeException("Failed to load .env file", e);

        }

    }

}