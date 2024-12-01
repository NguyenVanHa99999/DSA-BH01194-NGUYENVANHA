package Helper;

import Assignment.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class readCSV {
    // Read data from a CSV file and convert it to a list of Students
    public static List<Student> csvToArray(String filePath) {
        List<Student> students = new ArrayList<>();
        BufferedReader br = null;
        String line;
        String csvSplitBy = ",";

        try {
            br = new BufferedReader(new FileReader(filePath));
            // Read each line in the CSV file
            while ((line = br.readLine()) != null) {
                try {
                    Student student = new Student(line.split(csvSplitBy));
                    students.add(student);
                } catch (Exception e) {
                    logError("Error converting data for line: " + line + " - " + e.getMessage());
                }
            }
        } catch (IOException e) {
            logError("Unable to read CSV file: " + filePath + " - " + e.getMessage());
            System.out.println("Unable to read file: " + filePath);
        } finally {
            // Ensure the file is closed even if an error occurs
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    logError("Unable to close file: " + filePath + " - " + e.getMessage());
                }
            }
        }

        return students;
    }

    // Log errors to a log file
    private static void logError(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("error_log.txt", true))) {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.out.println("Unable to write error log: " + e.getMessage());
        }
    }
}
