package schooldatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import schooldatabase.model.Department;

public class DepartmentFileManager {
    ArrayList<Department> departments = new ArrayList<Department>();
    String filename;

    DepartmentFileManager(String filename) {
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
                    int departmentId = Integer.parseInt(c[0]);
                    String departmentName = c[1];
                    // Create department object using variables
                    Department department = new Department(departmentId, departmentName);
                    departments.add(department);// Add department Object to array list
                }
                FileScanner.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error" + e.getMessage());
        }
    }
}
