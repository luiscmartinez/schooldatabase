package schooldatabase;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import schooldatabase.model.Student;
import javafx.event.EventHandler;

public class ShowStudentView {
    private StudentFileManager studentFileManager;
    private GridPane formPane;

    public ShowStudentView(StudentFileManager studentFileManager) {
        this.studentFileManager = studentFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        GridPane formPane = new GridPane();
        Label inputLabel = new Label("Student ID");
        TextField studentIdInput = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {

                        Student student;
                        try {
                            student = studentFileManager.getStudent(Integer.parseInt(studentIdInput.getText()));
                            StudentFormGenerator studentForm = new StudentFormGenerator();
                            studentForm.prepopulateForm(student);
                            formPane.add(studentForm.createForm("View Student Read Only Form"), 0, 1);
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
        formPane.add(studentIdInput, 1, 0);
        formPane.add(searchButton, 2, 0);
        return formPane;
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}