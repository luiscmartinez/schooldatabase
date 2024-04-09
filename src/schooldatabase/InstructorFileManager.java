package schooldatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import schooldatabase.model.Instructor;

public class InstructorFileManager {
    ArrayList<Instructor> instructors = new ArrayList<Instructor>();
    String filename;

    InstructorFileManager(String filename) {
        try {
            this.filename = filename;
            File file = new File(filename);
            Scanner FileScanner = new Scanner(file); // Create File scanner
            // Read File line by line
            if (file.exists()) {
                while (FileScanner.hasNext()) {
                    String line = FileScanner.nextLine();// read next line
                    String[] c = line.split(",");// Split line into a string array
                    // Asign the element of array to variables
                    int instructorId = Integer.parseInt(c[0]);
                    String instructorName = c[1];
                    String instructorDepartment = c[2];
                    // Create department object using variables
                    Instructor instructor = new Instructor(instructorId, instructorName, instructorDepartment);
                    instructors.add(instructor);// Add department Object to array list
                }
                FileScanner.close();
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}
