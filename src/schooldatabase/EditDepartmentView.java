package schooldatabase;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import schooldatabase.model.Department;

public class EditDepartmentView {
    private DepartmentFileManager departmentFileManager;
    private GridPane formPane;

    public EditDepartmentView(DepartmentFileManager departmentFileManager) {
        this.departmentFileManager = departmentFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        GridPane formPane = new GridPane();

        Label inputLabel = new Label("Department ID");
        TextField initialInput = new TextField();
        initialInput.setPromptText("ID");
        // the search department id btn
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            try {
                int departmentId = Integer.parseInt(initialInput.getText());
                DepartmentFormGenerator departmentForm = new DepartmentFormGenerator();
                Department department = departmentFileManager.getDepartment(departmentId);
                if (department == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Department not found", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
                departmentForm.prepopulateForm(department.getName());
                departmentForm.configureActionButton("Update Department", e -> {
                    try {
                        departmentFileManager.updateDepartment(departmentId, departmentForm.getName());
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Department Updated", ButtonType.OK);
                        alert.showAndWait();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                formPane.add(departmentForm.createForm("Edit Department"), 0, 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        formPane.add(inputLabel, 0, 0);
        formPane.add(initialInput, 1, 0);
        formPane.add(searchButton, 2, 0);
        return formPane;
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
