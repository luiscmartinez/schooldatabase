package schooldatabase;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import schooldatabase.database.DatabaseConnection;
import schooldatabase.model.Enrollment;
import schooldatabase.model.GenericList;

public class EnrollmentFileManager {
    GenericList<Enrollment> enrollments = new GenericList<Enrollment>();

    public EnrollmentFileManager() throws EmptyFieldException, IOException {
        String selectSql = "SELECT * FROM enrollments;";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(selectSql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int student_id = rs.getInt("student_id");
                int course_id = rs.getInt("course_id");
                String year = rs.getString("year");
                String semester = rs.getString("semester");
                char grade = rs.getString("grade").charAt(0);
                Enrollment enrollment = new Enrollment(id, student_id, course_id, year, semester, grade);
                enrollments.add(enrollment);
            }
            rs.close();
            pstmt.close();
            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error occurred during the select enrollments operation.");
            e.printStackTrace();
        }
    }

    boolean addEnrollment(int course_id, int student_id, String year, String semester, char grade)
            throws IOException, EmptyFieldException {
        // If Enrollment does not exsist then add it to the arraylist and return true
        if (year.equals("") || semester == null || grade == ' ') {
            throw new EmptyFieldException("One or More Fields Are Empty");
        } else {
            String insertSQL = "INSERT INTO enrollments (course_id, student_id, year, semester, grade) VALUES (?, ?, ?, ?, ?);";
            try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, course_id);
                pstmt.setInt(2, student_id);
                pstmt.setString(3, year);
                pstmt.setString(4, semester);
                pstmt.setString(5, Character.toString(grade));
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating Enrollment failed, no rows affected");
                }
                // Get the last inserted id
                // String selectSql = "SELECT LAST_INSERT_ID() as eid;";
                // try (PreparedStatement pstmt2 = conn.prepareStatement(selectSql);
                // ResultSet rs = pstmt2.executeQuery()) {
                // rs.next();
                // int eid = rs.getInt("eid");
                // Enrollment enrollment = new Enrollment(eid, course_id, student_id, year,
                // semester, grade);
                // enrollments.add(enrollment);
                // rs.close();
                // pstmt2.close();
                // }
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Enrollment enrollment = new Enrollment(generatedKeys.getInt(1), course_id, student_id, year,
                                semester, grade);
                        enrollments.add(enrollment);
                    } else {
                        throw new SQLException("Creating Enrollment failed, no ID obtain");
                    }
                }
                DatabaseConnection.closeConnection();
                return true;
            } catch (SQLException e) {
                System.out.println("Error occurred during the insert operation.");
                e.printStackTrace();
                return false;
            }
        }
    }

    Enrollment getEnrollment(int eid)
            throws EmptyFieldException, IOException {
        Enrollment current;
        for (int i = 0; i < enrollments.size(); i++) {
            current = enrollments.get(i);
            int Eid = current.getID();
            if (Eid == eid) {
                return current;
            }
        }
        return null;
    }

    boolean updateEnrollment(int id, int student_id, int course_id, String year, String semester, char grade)
            throws IOException, EmptyFieldException {
        if (year.equals("") || semester == null || grade == ' ') {
            throw new EmptyFieldException("One or More Fields Are Empty");
        } else {
            String updateSQL = "UPDATE enrollments SET student_id = ?, course_id = ?, year = ?, semester = ?, grade = ? WHERE id = ?;";
            try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                pstmt.setInt(1, student_id);
                pstmt.setInt(2, course_id);
                pstmt.setString(3, year);
                pstmt.setString(4, semester);
                pstmt.setString(5, Character.toString(grade));
                pstmt.setInt(6, id);
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Updating Enrollment failed, no rows affected");
                }
                Enrollment enrollment = getEnrollment(id);
                enrollment.setStudentID(student_id);
                enrollment.setCourseID(course_id);
                enrollment.setYear(year);
                enrollment.setSemester(semester);
                enrollment.setGrade(grade);

                DatabaseConnection.closeConnection();
                return true;
            } catch (SQLException e) {
                System.out.println("Error occurred during the update operation.");
                e.printStackTrace();
                return false;
            }
        }
    }
}