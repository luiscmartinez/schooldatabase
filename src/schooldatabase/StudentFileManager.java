package schooldatabase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import schooldatabase.model.GenericList;
import schooldatabase.model.Student;

public class StudentFileManager {
    String filename;
    GenericList<Student> students = new GenericList<>();

    public StudentFileManager(String filename) {
        this.filename = filename;
        try {
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] s = line.split(",");
                int sid = Integer.parseInt(s[0]);
                String firstname = s[1];
                String lastname = s[2];
                String address = s[3];
                String city = s[4];
                String state = s[5];
                String zip = s[6];
                Student stud = new Student(sid, firstname, lastname, address, city, state, zip);
                students.add(stud);
            }
            fileScanner.close();
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        }
    }

    public boolean addStudent(int id, String firstName, String lastName, String address, String city, String zip,
            String state) throws EmptyFieldException, IOException {
        if (getStudent(id) == null) {
            if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || city.isEmpty() || state == null
                    || zip.isEmpty()) {
                throw new EmptyFieldException("One or More Fields Are Empty");
            } else {
                Student stud = new Student(id, firstName, lastName, address, city, state, zip);
                students.add(stud);
                try (FileWriter fwriter = new FileWriter(filename, true);
                        PrintWriter outputFile = new PrintWriter(fwriter)) {
                    outputFile.println(stud.toString());
                }
                System.out.println("Student has been added");
                return true;
            }
        } else {
            System.out.println("Student Already Exists");
            return false;
        }
    }

    public Student getStudent(int id) throws EmptyFieldException, IOException {
        if (id == 0) {
            throw new EmptyFieldException("Not a Valid ID");
        } else {
            for (int i = 0; i < students.size(); i++) {
                Student current = students.get(i);
                int ID = current.id;
                if (ID == id)
                    return current;
            }
            return null;
        }
    }

    public boolean updateStudent(int id, String firstName, String lastName, String address, String city, String state,
            String zip) throws IOException, EmptyFieldException {
        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || city.isEmpty() || state == null
                || zip.isEmpty()) {
            throw new EmptyFieldException("One or More Fields Are Empty");
        } else {
            Student stud = getStudent(id);
            if (stud != null) {
                stud.setFirstName(firstName);
                stud.setLastName(lastName);
                stud.setAddress(address);
                stud.setCity(city);
                stud.setState(state);
                stud.setZip(zip);
                try (FileWriter fwriter = new FileWriter(filename); PrintWriter outputFile = new PrintWriter(fwriter)) {

                    for (int i = 0; i < students.size(); i++) {
                        Student s = students.get(i);
                        outputFile.println(s.toString());
                    }
                }
                return true;
            }
        }
        return false;
    }
}
