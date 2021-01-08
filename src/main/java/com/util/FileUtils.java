package com.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

    public static List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Cannot read the file. Please verify the path");
        }
    }

    public static void writeToFile(List<String> lines, String filePath) {
        try {
            Files.write(Paths.get(filePath), lines);
        } catch (IOException e) {
            throw new RuntimeException("Cannot create the report. Please verify the output file path");
        }
    }
}
