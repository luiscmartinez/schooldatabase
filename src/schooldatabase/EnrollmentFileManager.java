package schooldatabase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import schooldatabase.model.Enrollment;

public class EnrollmentFileManager {
    String filename;
    ArrayList<Enrollment> enrollments = new ArrayList<Enrollment>();// arrayList<Enrollment>

    // Constructure
    EnrollmentFileManager(String filename) {
        try {
            this.filename = filename;
            File file = new File(filename);
            Scanner FileScanner = new Scanner(file); // Create File scanner
            // Read File line by line
            if (file.exists()) {
                while (FileScanner.hasNext()) {
                    String line = FileScanner.nextLine();// read next line
                    String[] e = line.split(",");// Split line into a string array
                    // Asign the element of array to variables
                    int EID = Integer.parseInt(e[0]);
                    int SID = Integer.parseInt(e[1]);
                    int CID = Integer.parseInt(e[2]);
                    String Year = e[3];
                    String Semester = e[4];
                    char Grade = e[5].charAt(0);
                    // Create course object using variables
                    Enrollment enrollment = new Enrollment(EID, SID, CID, Year, Semester, Grade);
                    enrollments.add(enrollment);// Add course Object to array list
                }
                FileScanner.close();
            }
        } catch (IOException ioe) {
            System.out.println("Error" + ioe.getMessage());
        }
    }

    boolean addEnrollment(int EID, int CID, int SID, String year, String semester, char grade)
            throws IOException, EmptyFieldException {
        // If Enrollment does not exsist then add it to the arraylist and return true
        if (year.equals("") || semester == null || grade == ' ') {
            throw new EmptyFieldException("One or More Fields Are Empty");
        } else {
            if (getEnrollment(EID) == null) {
                Enrollment enroll = new Enrollment(EID, SID, CID, year, semester, grade);
                enrollments.add(enroll);

                // Append the new course object to the file
                FileWriter fwriter = new FileWriter(filename, true);
                PrintWriter outputFile = new PrintWriter(fwriter);
                outputFile.println(EID + "," + SID + "," + CID + "," + year + ","
                        + semester + "," + grade);
                outputFile.close();

                System.out.println("Enrollment Has Been Added");
                return true;
            }
            System.out.println("Enrollment Already Exists");
            return false;
        }
    }

    Enrollment getEnrollment(int eid)
            throws EmptyFieldException, IOException {
        Enrollment current;
        for (int i = 0; i < enrollments.size(); i++) {
            current = enrollments.get(i);
            int Eid = current.getEID();
            if (Eid == eid) {
                return current;
            }
        }
        return null;
    }
}