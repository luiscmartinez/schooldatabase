package schooldatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import schooldatabase.database.DatabaseConnection;
import schooldatabase.model.GenericList;
import schooldatabase.model.Student;

public class StudentFileManager {
    GenericList<Student> students = new GenericList<>();

    public StudentFileManager() throws EmptyFieldException, IOException {
        String selectSql = "SELECT * FROM students;";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(selectSql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zip = rs.getString("zipcode");
                Student student = new Student(id, firstName, lastName, address, city, state, zip);
                students.add(student);
            }
            rs.close();
            pstmt.close();
            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error occurred during the search operation.");
            e.printStackTrace();
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
                    PreparedStatement pstmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, address);
                pstmt.setString(4, city);
                pstmt.setString(5, zip);
                pstmt.setString(6, state);
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating Student failed, no rows affected");
                }
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Student student = new Student(generatedKeys.getInt(1), firstName, lastName, address, city,
                                state, zip);
                        students.add(student);
                    } else {
                        throw new SQLException("Creating Student failed, no ID obtain");
                    }
                }
                conn.close();
                pstmt.close();
                DatabaseConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("Error occurred during data insertion.");
                e.printStackTrace();
            }
            System.out.println("Student has been added");
            return true;
        }
    }

    public Student getStudent(int id) throws EmptyFieldException, IOException {

        for (int i = 0; i < students.size(); i++) {
            Student current = students.get(i);
            int ID = current.getId();
            if (ID == id) {
                return current;
            }
        }

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
                    rs.close();
                    pstmt.close();
                    Student student = new Student(id, firstName, lastName, address, city, state, zip);
                    students.add(student);
                    DatabaseConnection.closeConnection();
                    return student;
                } else {
                    throw new EmptyFieldException("Not a Valid ID");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during the search operation.");
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateStudent(int id, String firstName, String lastName, String address, String city, String state,
            String zip) throws IOException, EmptyFieldException {
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
                Student student = getStudent(id);
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setAddress(address);
                student.setCity(city);
                student.setState(state);
                student.setZip(zip);
                System.out.println("A student was updated successfully!");
                pstmt.close();
                DatabaseConnection.closeConnection();
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during data update.");
            e.printStackTrace();
        }

        return false;
    }
}
