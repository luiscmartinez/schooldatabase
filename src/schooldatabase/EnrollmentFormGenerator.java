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
    private Button studentSearchButton;
    private Button courseSearchButton;
    private TextField studentNameField; // Field to display student name
    private TextField courseNameField; // Field to display course name

    public EnrollmentFormGenerator() {
        this.studentIdField = new TextField();
        courseIdField = new TextField();
        yearField = new TextField();
        gradeComboBox = new ComboBox<>();
        gradeComboBox.getItems().addAll('A', 'B', 'C', 'D', 'F');
        semesterComboBox = new ComboBox<>(FXCollections.observableArrayList("Fall", "Spring", "Summer", "Winter"));
        actionButton = new Button();
        studentSearchButton = new Button("Search Student");
        courseSearchButton = new Button("Search Course");
        studentNameField = new TextField(); // Initialize the text field for student name
        studentNameField.setEditable(false); // Make it read-only
        courseNameField = new TextField(); // Initialize the text field for course name
        courseNameField.setEditable(false); // Make it read-only
    }

    public GridPane createFormPane(String formTitle) {
        GridPane formPane = new GridPane();

        Label titleLabel = new Label(formTitle);
        titleLabel.setFont(new Font("Arial", 18));
        formPane.add(titleLabel, 0, 0);
        formPane.add(new Label("Student ID:"), 0, 1);
        formPane.add(studentIdField, 1, 1);
        formPane.add(studentSearchButton, 2, 1); // Add the search button next to the studentIdField
        formPane.add(new Label("Course ID:"), 0, 2);
        formPane.add(courseIdField, 1, 2);
        formPane.add(courseSearchButton, 2, 2); // Add the search button next to the courseIdField
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
        formPane.add(new Label("Student Name:"), 0, 7); // Add label for student name
        formPane.add(studentNameField, 1, 7); // Add the studentNameField below the studentIdField
        formPane.add(new Label("Course Name:"), 0, 8); // Add label for course name
        formPane.add(courseNameField, 1, 8); // Add the courseNameField below the courseIdField
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

    public void configureStudentSearchButton(Consumer<ActionEvent> searchAction) {
        studentSearchButton.setOnAction(searchAction::accept);
    }

    public void configureCourseSearchButton(Consumer<ActionEvent> searchAction) {
        courseSearchButton.setOnAction(searchAction::accept);
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
        if (gradeComboBox.getValue() == null) // Check if the gradeComboBox has a value selected
            return ' '; // Return a space character (invalid grade
        return gradeComboBox.getValue().charValue();
    }

    public String getSemesterComboBox() {
        if (semesterComboBox.getValue() == null) // Check if the semesterComboBox has a value selected
            return ""; // Return an empty string (invalid semester
        return semesterComboBox.getValue();
    }

    public void clearForm() {
        studentIdField.clear();
        courseIdField.clear();
        yearField.clear();
        gradeComboBox.setValue(null);
        semesterComboBox.setValue(null);
        studentNameField.clear(); // Clear the student name field
        courseNameField.clear(); // Clear the course name field
    }

    public void setStudentNameField(String studentName) {
        studentNameField.setText(studentName); // Set the student name in the text field
    }

    public void setCourseNameField(String courseName) {
        courseNameField.setText(courseName); // Set the course name in the text field
    }
}
