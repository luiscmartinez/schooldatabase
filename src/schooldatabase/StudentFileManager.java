package schooldatabase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import schooldatabase.database.DatabaseConnection;
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

    public boolean addStudent(String firstName, String lastName, String address, String city, String zip,
            String state) throws EmptyFieldException, IOException {
        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || city.isEmpty() || state == null
                || zip.isEmpty()) {
            throw new EmptyFieldException("One or More Fields Are Empty");
        } else {

            String insertSQL = "INSERT INTO students (first_name, last_name, address, city, zipcode, state) VALUES (?, ?, ?, ?, ?, ?);";
            try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, address);
                pstmt.setString(4, city);
                pstmt.setString(5, zip);
                pstmt.setString(6, state);
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("A new student was inserted successfully!");
                    DatabaseConnection.closeConnection();
                }
            } catch (SQLException e) {
                System.out.println("Error occurred during data insertion.");
                e.printStackTrace();
            }
            System.out.println("Student has been added");
            return true;
        }
    }

    public Student getStudent(int id) throws EmptyFieldException, IOException {
        String selectSQL = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String address = rs.getString("address");
                    String city = rs.getString("city");
                    String state = rs.getString("state");
                    String zip = rs.getString("zipcode");
                    System.out.println("Student ID: duhhhhh" + firstName + lastName + address + city + state + zip);
                    rs.close();
                    pstmt.close();
                    Student student = new Student(id, firstName, lastName, address, city, state, zip);
                    DatabaseConnection.closeConnection();
                    return student;
                } else {
                    throw new EmptyFieldException("Not a Valid ID");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during the search operation.");
            e.printStackTrace();
            // return "An error occurred while searching for the user.";
        }
        return null;
    }

    public boolean updateStudent(int id, String firstName, String lastName, String address, String city, String state,
            String zip) throws IOException, EmptyFieldException {
        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || city.isEmpty() || state == null
                || zip.isEmpty()) {
            throw new EmptyFieldException("One or More Fields Are Empty");
        } else {
            String updateSQL = "UPDATE students SET first_name = ?, last_name = ?, address = ?, city = ?, state = ?, zipcode = ? WHERE id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, address);
                pstmt.setString(4, city);
                pstmt.setString(5, state);
                pstmt.setString(6, zip);
                pstmt.setInt(7, id);
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("A student was updated successfully!");
                    pstmt.close();
                    DatabaseConnection.closeConnection();
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Error occurred during data update.");
                e.printStackTrace();
            }
        }
        return false;
    }
}
