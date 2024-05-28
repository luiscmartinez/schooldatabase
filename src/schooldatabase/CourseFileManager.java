package schooldatabase;

import schooldatabase.database.DatabaseConnection;
import schooldatabase.model.Course;
import schooldatabase.model.GenericList;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class CourseFileManager {
    GenericList<Course> courses = new GenericList<Course>();
    String filename;

    // Constructor
    CourseFileManager() {
        String selectSql = "SELECT * FROM courses;";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(selectSql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int department_id = rs.getInt("department_id");
                int instructor_id = rs.getInt("instructor_id");
                Course course = new Course(id, name, description, department_id, instructor_id);
                courses.add(course);
            }
            rs.close();
            pstmt.close();
            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error occurred during the search operation.");
            e.printStackTrace();
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

    boolean addCourse(String courseName, String description, int departmentID, int instructorID)
            throws EmptyFieldException, IOException {
        String insertSql = "INSERT INTO courses (name, description, department_id, instructor_id) VALUES (?, ?, ?, ?);";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {
            pstmt.setString(1, courseName);
            pstmt.setString(2, description);
            pstmt.setInt(3, departmentID);
            pstmt.setInt(4, instructorID);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Course failed, no rows affected");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Course course = new Course(generatedKeys.getInt(1), courseName, description, departmentID,
                            instructorID);
                    courses.add(course);
                } else {
                    throw new SQLException("Creating Course failed, no ID obtained.");
                }
            }
            conn.close();
            pstmt.close();
            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error occurred during the insert operation.");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    boolean updateCourse(int cid, String courName, String courseDescription, int departmentID,
            int instructor)
            throws IOException, EmptyFieldException {
        String updateSQL = "UPDATE courses SET name = ?, description = ?, department_id = ?, instructor_id = ? WHERE id = ?;";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(updateSQL);) {
            pstmt.setString(1, courName);
            pstmt.setString(2, courseDescription);
            pstmt.setInt(3, departmentID);
            pstmt.setInt(4, instructor);
            pstmt.setInt(5, cid);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating Course failed, no rows affected");
            } else {
                Course course = getCourse(cid);
                course.setName(courName);
                course.setDescription(courseDescription);
                course.setDepartmentID(departmentID);
                course.setInstructor(instructor);
            }
            conn.close();
            pstmt.close();
            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error occurred during the update operation.");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}