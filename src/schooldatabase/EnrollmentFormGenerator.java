package schooldatabase;

import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class EnrollmentFormGenerator {
    private TextField studentIdField;
    private TextField courseIdField;
    private TextField yearField;
    private ComboBox<Character> gradeComboBox;
    private ComboBox<String> semesterComboBox;
    private Button actionButton;

    public EnrollmentFormGenerator() {
        this.studentIdField = new TextField();
        courseIdField = new TextField();
        yearField = new TextField();
        gradeComboBox = new ComboBox<>();
        gradeComboBox.getItems().addAll('A', 'B', 'C', 'D', 'F');
        semesterComboBox = new ComboBox<>(FXCollections.observableArrayList("Fall", "Spring", "Summer", "Winter"));
        actionButton = new Button();
    }

    public GridPane createFormPane(String formTitle) {
        GridPane formPane = new GridPane();

        Label titleLabel = new Label(formTitle);
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
        if (!actionButton.getText().isEmpty()) {
            formPane.add(actionButton, 0, 6);
        } else {
            studentIdField.setEditable(false);
            courseIdField.setEditable(false);
            yearField.setEditable(false);
            gradeComboBox.setDisable(true);
            semesterComboBox.setDisable(true);
        }
        return formPane;
    }

    public void prepopulateForm(int studentId, int courseId, String year, String semester, char grade) {
        studentIdField.setText(Integer.toString(studentId));
        courseIdField.setText(Integer.toString(courseId));
        yearField.setText(year);
        semesterComboBox.setValue(semester);
        gradeComboBox.setValue(grade);
    }

    public void configureActionButton(String label, Consumer<ActionEvent> action) {
        actionButton.setText(label);
        actionButton.setOnAction(action::accept);
    }

    public TextField getStudentIdField() {
        return studentIdField;
    }

    public TextField getCourseIdField() {
        return courseIdField;
    }

    public TextField getYearField() {
        return yearField;
    }

    public char getGradeComboBox() {
        return gradeComboBox.getValue().charValue();
    }

    public String getSemesterComboBox() {
        return semesterComboBox.getValue();
    }
}
