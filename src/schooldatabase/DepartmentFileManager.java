package schooldatabase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public boolean addDepartment(int departmentId, String departmentName) throws IOException {
        Department department = new Department(departmentId, departmentName);
        try (FileWriter fwriter = new FileWriter(filename, true);
                PrintWriter outputFile = new PrintWriter(fwriter)) {
            outputFile.println(department.toString());
        }
        if (getDepartment(departmentId) == null) {
            departments.add(department);
            return true;
        } else {
            return false;
        }
    }

    public Department getDepartment(int departmentId) {
        for (Department department : departments) {
            if (department.getId() == departmentId) {
                return department;
            }
        }
        return null;
    }

    public ArrayList<String> getDepartmentNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Department department : departments) {
            names.add(department.getName());
        }
        return names;
    }

}
