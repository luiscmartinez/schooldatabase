package schooldatabase;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import schooldatabase.model.Instructor;

public class EditInstructorView {
    private GridPane formPane;
    private InstructorFileManager instructorFileManager;
    private DepartmentFileManager departmentFileManager;

    public EditInstructorView(InstructorFileManager instructorFileManager,
            DepartmentFileManager departmentFileManager) {
        this.instructorFileManager = instructorFileManager;
        this.departmentFileManager = departmentFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        GridPane formPane = new GridPane();

        Label inputLabel = new Label("Instructor ID");
        TextField initialInput = new TextField();
        initialInput.setPromptText("ID");
        Button searchButton = new Button("Search");

        searchButton.setOnAction(event -> {
            try {
                int instructorId = Integer.parseInt(initialInput.getText());
                InstructorFormGenerator instructorForm = new InstructorFormGenerator(
                        departmentFileManager.getDepartmentNames());

                Instructor instructor = instructorFileManager.getInstructor(instructorId);
                if (instructor == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Instructor not found", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
                String departmentName = departmentFileManager.getDepartment(instructor.getDepartmentID()).getName();
                instructorForm.prepopulateForm(instructor.getName(), departmentName);
                instructorForm.configureActionButton("Update Instructor", e -> {
                    if (instructorForm.getName().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill out all fields", ButtonType.OK);
                        alert.showAndWait();
                        return;
                    }
                    try {
                        int departmentID = departmentFileManager.getDepartmentByName(instructorForm.getDepartment())
                                .getId();
                        instructorFileManager.updateInstructor(instructorId, instructorForm.getName(),
                                departmentID);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Instructor Updated", ButtonType.OK);
                        alert.showAndWait();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                formPane.add(instructorForm.createForm("Edit Instructor Form"), 0, 1);
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
