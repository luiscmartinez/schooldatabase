package schooldatabase;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import schooldatabase.model.Department;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class ShowDepartmentView {
    private DepartmentFileManager departmentFileManager;
    private GridPane formPane;

    public ShowDepartmentView(DepartmentFileManager departmentFileManager) {
        // TODO Auto-generated constructor stub
        this.departmentFileManager = departmentFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        GridPane formPane = new GridPane();
        Label inputLabel = new Label("Department ID");
        TextField departmentIdInput = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        Department department;
                        try {
                            department = departmentFileManager
                                    .getDepartment(Integer.parseInt(departmentIdInput.getText()));
                            if (department == null) {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Department was not Found",
                                        ButtonType.OK);
                                alert.showAndWait();
                                return;
                            }

                            DepartmentFormGenerator departmentForm = new DepartmentFormGenerator();
                            departmentForm.prepopulateForm(department.getName());
                            formPane.add(departmentForm.createForm("View Department Read Only Form"), 0, 1);
                        } catch (NumberFormatException NFE) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "ID Field was Left Blank", ButtonType.OK);
                            alert.showAndWait();
                        } catch (Exception exc) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, exc.getMessage(), ButtonType.OK);
                            alert.showAndWait();
                        }

                    }
                });
        formPane.add(inputLabel, 0, 0);
        formPane.add(departmentIdInput, 1, 0);
        formPane.add(searchButton, 2, 0);
        return formPane;
    }

    public GridPane getFormPane() {
        return this.formPane;
    }

}
