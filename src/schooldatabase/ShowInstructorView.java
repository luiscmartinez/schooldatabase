package schooldatabase;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import schooldatabase.model.Department;
import schooldatabase.model.Instructor;
import javafx.event.ActionEvent;

public class ShowInstructorView {
    private InstructorFileManager instructorFileManager;
    private DepartmentFileManager departmentFileManager;
    private GridPane formPane;

    public ShowInstructorView(InstructorFileManager instructorFileManager,
            DepartmentFileManager departmentFileManager) {
        this.instructorFileManager = instructorFileManager;
        this.departmentFileManager = departmentFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        GridPane formPane = new GridPane();
        Label inputLabel = new Label("Instructor ID");
        TextField instructorIdInput = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        Instructor instructor;
                        try {
                            instructor = instructorFileManager
                                    .getInstructor(Integer.parseInt(instructorIdInput.getText()));
                            if (instructor == null) {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Instructor was not Found",
                                        ButtonType.OK);
                                alert.showAndWait();
                                return;
                            }

                            InstructorFormGenerator instructorForm = new InstructorFormGenerator();
                            Department department = departmentFileManager.getDepartment(instructor.getDepartmentID());
                            instructorForm.prepopulateForm(instructor.getName(), department.getName());
                            formPane.add(instructorForm.createForm("View Instructor Read Only Form"), 0, 1);
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
        formPane.add(instructorIdInput, 1, 0);
        formPane.add(searchButton, 2, 0);
        return formPane;
    }

    public GridPane getFormPane() {
        return this.formPane;
    }

}
