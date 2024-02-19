package schooldatabase;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class EnrollmentFormGenerator {
    private EnrollmentFileManager enrollmentFileManager;
    private StudentFileManager studentFileManager;
    private CourseFileManager courseFileManager;
    private TextField studentIdField;
    private TextField courseIdField;
    private TextField yearField;
    private ComboBox<Character> gradeComboBox;
    private ComboBox<String> semesterComboBox;
    private Button actionButton;
    private GridPane formPane;

    public EnrollmentFormGenerator(EnrollmentFileManager enrollmentFileManager, StudentFileManager studentFileManager,
            CourseFileManager courseFileManager) {
        this.enrollmentFileManager = enrollmentFileManager;
        this.studentFileManager = studentFileManager;
        this.courseFileManager = courseFileManager;
        this.studentIdField = new TextField();
        courseIdField = new TextField();
        yearField = new TextField();
        gradeComboBox = new ComboBox<>();
        gradeComboBox.getItems().addAll('A', 'B', 'C', 'D', 'F');
        semesterComboBox = new ComboBox<>(FXCollections.observableArrayList("Fall", "Spring", "Summer", "Winter"));
        actionButton = new Button();
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        GridPane formPane = new GridPane();

        Label titleLabel = new Label("New Enrollment Form");
        titleLabel.setFont(new Font("Arial", 18));
        formPane.add(titleLabel, 0, 0);
        formPane.add(new Label("Student ID:"), 0, 1);
        formPane.add(studentIdField, 1, 1);
        formPane.add(new Label("Course ID:"), 0, 2);
        formPane.add(courseIdField, 1, 2);
        formPane.add(new Label("Year:"), 0, 3);
        formPane.add(yearField, 1, 3);
        formPane.add(new Label("Grade:"), 0, 4);
        formPane.add(gradeComboBox, 1, 4);
        formPane.add(new Label("Semester:"), 0, 5);
        formPane.add(semesterComboBox, 1, 5);
        actionButton.setText("Add Enrollment");
        formPane.add(actionButton, 0, 6);
        final int enrollmentID = enrollmentFileManager.enrollments.size() + 1;
        actionButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int courseId = Integer.parseInt(courseIdField.getText());
                        int studentId = Integer.parseInt(studentIdField.getText());
                        String year = yearField.getText();
                        char grade = gradeComboBox.getValue();
                        String semester = semesterComboBox.getValue();
                        try {

                            if (gradeComboBox.getValue() == null) {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Select a Grade ", ButtonType.OK);
                                alert.showAndWait();
                            } else if (!enrollmentFileManager.addEnrollment(enrollmentID, courseId, studentId, year,
                                    semester, grade)) {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "Enrollment Already Exists ",
                                        ButtonType.OK);
                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.NONE,
                                        "Enrollment ID: " + enrollmentID + "\nCourse ID: " + courseId + "\nStudent ID: "
                                                + studentId + "\nSemester: " + semester + " " + year + "\nGrade: "
                                                + grade,
                                        ButtonType.OK);
                                alert.setHeaderText("Enrollment Was Added");
                                alert.showAndWait();
                            }

                        } catch (EmptyFieldException EFE) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, EFE.getMessage(), ButtonType.OK);
                            alert.showAndWait();
                        } catch (NumberFormatException NFE) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "One or more ID Field was Left Blank",
                                    ButtonType.OK);
                            alert.showAndWait();
                        } catch (IOException ioe) {
                            System.out.println("Error: " + ioe.getMessage());
                        } catch (Exception exc) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, exc.getMessage(), ButtonType.OK);
                            alert.showAndWait();
                        }
                    }
                });

        return formPane;
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
