package schooldatabase;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class DepartmentActionHandler {
    private final DepartmentFormGenerator newDepartmentForm;
    private final DepartmentFileManager departmentFileManager;

    public DepartmentActionHandler(DepartmentFormGenerator newDepartmentForm,
            DepartmentFileManager departmentFileManager) {
        this.newDepartmentForm = newDepartmentForm;
        this.departmentFileManager = departmentFileManager;
    }

    public void handleAddDepartment() {
        System.out.println("Form submitted!");
        System.out.println("Department Name: " + newDepartmentForm.getName());
        final int departmentID = departmentFileManager.departments.size() + 1;
        System.out.println("departmentID: " + departmentID);
        String name = newDepartmentForm.getName();
        try {
            if (departmentFileManager.addDepartment(departmentID, name)) {
                System.out.println("Department added successfully!");
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Department " + name + " added", ButtonType.OK);
                alert.setHeaderText("Department Added");
                newDepartmentForm.clearForm();
                alert.showAndWait();
            } else {
                System.out.println("Failed to add department!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
