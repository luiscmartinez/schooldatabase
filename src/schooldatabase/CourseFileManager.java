package schooldatabase;

import java.util.Scanner;

import schooldatabase.model.Course;
import schooldatabase.model.GenericList;

import java.io.*;

class CourseFileManager {
    GenericList<Course> courses = new GenericList<Course>();
    String filename;

    // Constructor
    CourseFileManager(String filename) {
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
                    int courseId = Integer.parseInt(c[0]);
                    String courseName = c[1];
                    String courseDescription = c[2];
                    // Create course object using variables
                    Course course = new Course(courseId, courseName, courseDescription);
                    courses.add(course);// Add course Object to array list
                }
                FileScanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    Course getCourse(int cid) throws EmptyFieldException, IOException {
        // try{
        if (cid == 0) {
            throw new EmptyFieldException("Invalid Course ID");
        } else {
            for (int i = 0; i < courses.size(); i++) {
                Course current = courses.get(i);
                int ID = current.getCourseID();
                if (ID == cid)
                    return current;
            }
        }
        return null;

    }

    boolean addCourse(Course course) throws EmptyFieldException, IOException {
        // If course does not exist collect info, create new course object and add to
        // arraylist and return true
        int CID = course.getCourseID();
        String courseName = course.getName();
        String courseDescrip = course.getDescription();

        if (getCourse(CID) == null) {// Call getCourse method to find if course exis
            if (courseName.equals("") || courseDescrip.equals("")) {
                throw new EmptyFieldException("Course ID Field is Blank");
            } else {
                courses.add(course);

                // Append the new course object to the file
                FileWriter fwriter = new FileWriter(filename, true);
                PrintWriter outputFile = new PrintWriter(fwriter);
                outputFile.println(CID + "," + courseName + "," + courseDescrip);
                outputFile.close();

                System.out.println("Course has been added");// Confirmation Statement
                return true;
            }
        } else {// If the course already exist then display an error message and return false
            System.out.println("Course Already Exists");
            return false;
        }
    }

    boolean updateCourse(int cid, String courName, String courseDescription) throws IOException, EmptyFieldException {
        // Check if course exists, if it does then update the course object and return
        // true
        if (getCourse(cid) != null) {
            Course cour = getCourse(cid);
            cour.setID(cid);
            cour.setName(courName);
            cour.setDescription(courseDescription);
            // print every course in Arraylist in a wiped fi;e
            FileWriter fwriter = new FileWriter(filename);
            PrintWriter outputFile = new PrintWriter(fwriter);
            for (int i = 0; i < courses.size(); i++) {
                outputFile.println(
                        courses.get(i).getCourseID() + "," + courses.get(i).getName() + ","
                                + courses.get(i).getDescription());
            }
            outputFile.close();
            System.out.println("Course Has Been Updated");
            return true;
        }
        // if the course does not exist then display an error message and return false
        System.out.println("Course Does Not Exist");
        return false;
    }
}