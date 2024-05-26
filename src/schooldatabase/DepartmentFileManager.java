package schooldatabase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import schooldatabase.model.Department;
import schooldatabase.database.DatabaseConnection;

public class DepartmentFileManager {
    ArrayList<Department> departments = new ArrayList<Department>();

    DepartmentFileManager() {
        String selectSql = "SELECT * FROM departments;";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(selectSql);
                ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Department department = new Department(id, name);
                departments.add(department);
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

    public boolean addDepartment(String departmentName) throws IOException {
        String insertSql = "INSERT INTO departments (name) VALUES (?);";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, departmentName);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Department failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Department department = new Department(generatedKeys.getInt(1), departmentName);
                    departments.add(department);
                    DatabaseConnection.closeConnection();
                    return true;
                } else {
                    throw new SQLException("Creating Department failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during data insertion.");
            e.printStackTrace();
        }
        return false;
    }

    public Department getDepartment(int departmentId) throws EmptyFieldException, IOException {
        for (int i = 0; i < departments.size(); i++) {
            Department current = departments.get(i);
            int ID = current.getId();
            if (ID == departmentId) {
                return current;
            }
        }
        String selectSQL = "SELECT * FROM departments WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            pstmt.setInt(1, departmentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Department department = new Department(id, name);
                    departments.add(department);
                    DatabaseConnection.closeConnection();
                    return department;
                } else {
                    DatabaseConnection.closeConnection();
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred during select department operation");
            e.printStackTrace();
        }
        return null;
    }

    public Department getDepartmentByName(String departmentName) {
        for (Department department : departments) {
            if (department.getName().equals(departmentName)) {
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

    public boolean updateDepartment(int id, String name) throws IOException {
        String updateSQL = "UPDATE departments SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                Department department = getDepartment(id);
                department.setName(name);
                departments.add(department);
                return true;
            } else {
                throw new SQLException("Creating Student failed, no rows affected");
            }

        } catch (EmptyFieldException e) {
            e.notify();
        } catch (SQLException e) {
            System.out.println("Error occurred during data update.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
