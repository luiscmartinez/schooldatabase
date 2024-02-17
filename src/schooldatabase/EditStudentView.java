package schooldatabase;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import schooldatabase.model.Student;
import javafx.event.EventHandler;

public class EditStudentView {
    private StudentFileManager studentFileManager;
    private GridPane formPane;

    public EditStudentView(StudentFileManager studentFileManager) {
        this.studentFileManager = studentFileManager;
        // TODO: search for the student
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() { // Initialize the VBox with some padding
        GridPane formPane = new GridPane();

        Label inputLabel = new Label("Student ID");
        // Create the initial search form
        TextField initialInput = new TextField();
        initialInput.setPromptText("Id");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        Student student;
                        try {
                            student = studentFileManager.getStudent(Integer.parseInt(initialInput.getText()));
                            StudentFormGenerator studentForm = new StudentFormGenerator();
                            // studentForm.createForm();
                            studentForm.prepopulateForm(student.firstName, student.lastName, student.address,
                                    student.city,
                                    student.zip, student.state);
                            // todo: abstract this into studentActionHandler.handleUpdateStudent(id)
                            studentForm.configureActionButton("Update Student", event -> {
                                try {
                                    studentFileManager.updateStudent(student.id, studentForm.getFirstName(),
                                            studentForm.getLastName(), studentForm.getAddress(), studentForm.getCity(),
                                            studentForm.getState(), studentForm.getZipcode());
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Student Updated",
                                            ButtonType.OK);
                                    alert.showAndWait();
                                } catch (IOException IOE) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR, IOE.getMessage(), ButtonType.OK);
                                    alert.showAndWait();
                                } catch (EmptyFieldException EFE) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR, EFE.getMessage(), ButtonType.OK);
                                    alert.showAndWait();
                                } catch (NumberFormatException NFE) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR, "ID Field was Left Blank",
                                            ButtonType.OK);
                                    alert.showAndWait();
                                } catch (Exception exc) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR, exc.getMessage(), ButtonType.OK);
                                    alert.showAndWait();
                                }
                            });
                            formPane.add(studentForm.createForm("Edit Student Form"), 0, 1);
                        } catch (EmptyFieldException EFE) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, EFE.getMessage(), ButtonType.OK);
                            alert.showAndWait();
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
        formPane.add(initialInput, 1, 0);
        formPane.add(searchButton, 2, 0);
        return formPane;
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
