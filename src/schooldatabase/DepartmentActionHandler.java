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
        String name = newDepartmentForm.getName();
        try {
            if (name.isEmpty()) {
                throw new EmptyFieldException("Department name cannot be empty!");
            }
            if (departmentFileManager.addDepartment(name)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Department " + name + " added", ButtonType.OK);
                alert.setHeaderText("Department Added");
                newDepartmentForm.clearForm();
                alert.showAndWait();
            } else {
                System.out.println("Failed to add department!");
            }
        } catch (EmptyFieldException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
