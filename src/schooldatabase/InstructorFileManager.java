package schooldatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schooldatabase.database.DatabaseConnection;
import schooldatabase.model.Instructor;

public class InstructorFileManager {
    ArrayList<Instructor> instructors = new ArrayList<Instructor>();

    InstructorFileManager() {
        String selectSql = "SELECT * FROM instructors;";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(selectSql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int departmentID = rs.getInt("dept_id");
                Instructor instructor = new Instructor(id, name, departmentID);
                instructors.add(instructor);
            }
            rs.close();
            pstmt.close();
            DatabaseConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error occurred during the search operation.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());

        }

    }

    public boolean addInstructor(String instructorName, int departmentID)
            throws IOException {
        String insertSQL = "INSERT INTO instructors (name, dept_id) VALUES (?, ?);";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            // Log the SQL statement being executed
            System.out.println("Executing SQL: " + insertSQL);
            System.out.println("With parameters: name=" + instructorName + ", dept_id=" + departmentID);

            pstmt.setString(1, instructorName);
            pstmt.setInt(2, departmentID);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Instructor failed, no rows affected");
            }

            DatabaseConnection.closeConnection();
            return true; // Return true if the insertion was successful
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            e.printStackTrace();
        }
        return false;
    }

    public Instructor getInstructor(int instructorId) {
        for (Instructor instructor : instructors) {
            if (instructor.getId() == instructorId) {
                return instructor;
            }
        }
        return null;
    }

    public Instructor getInstructorByName(String instructorName) {
        for (Instructor instructor : instructors) {
            if (instructor.getName().equals(instructorName)) {
                return instructor;
            }
        }
        return null;
    }

    public ArrayList<Instructor> getInstructorsByDepartment(String department) {
        ArrayList<Instructor> instructorsByDepartment = new ArrayList<Instructor>();
        for (Instructor instructor : instructors) {
            instructorsByDepartment.add(instructor);
        }
        return instructorsByDepartment;
    }

    public int getDepartmentByName(String departmentName) {
        String selectSQL = "SELECT * FROM departments WHERE name = ? LIMIT 1;";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setString(1, departmentName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int departmentID = rs.getInt("id");
                    rs.close();
                    pstmt.close();
                    DatabaseConnection.closeConnection();
                    return departmentID;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during the search department by name operation.");
        }
        return 0;
    }

    public boolean updateInstructor(int id, String name, int departmentID) {
        String updateSQL = "UPDATE instructors SET name = ?, dept_id = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, departmentID);
            pstmt.setInt(3, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Instructor failed, no rows affected");
            }
            Instructor instructor = getInstructor(id);
            instructor.setDepartment(departmentID);
            instructor.setName(name);
            DatabaseConnection.closeConnection();
            return true;
        } catch (SQLException e) {
            System.out.println("Error occurred during the search operation.");
            e.printStackTrace();
        }
        return false;
    }
}
