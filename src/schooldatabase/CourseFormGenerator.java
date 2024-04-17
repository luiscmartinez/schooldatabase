package schooldatabase;

import java.util.List;
import java.util.function.Consumer;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import schooldatabase.model.Course;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

public class CourseFormGenerator {
    private TextField courseNameField;
    private TextField courseDescriptionField;
    private ComboBox<String> departmentComboBox;
    private ComboBox<String> instructorComboBox;
    private Button actionButton;
    private DepartmentFileManager departmentFileManager;
    private InstructorFileManager instructorFileManager;

    public CourseFormGenerator(DepartmentFileManager departmentFileManager,
            InstructorFileManager instructorFileManager) {
        this.departmentFileManager = departmentFileManager;
        this.instructorFileManager = instructorFileManager;
        // Initialize form components
        courseNameField = new TextField();
        courseDescriptionField = new TextField();
        departmentComboBox = new ComboBox<>(
                FXCollections.observableArrayList(departmentFileManager.getDepartmentNames()));
        actionButton = new Button();
        instructorComboBox = new ComboBox<>();

        departmentComboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            updateInstructorComboBox(newValue); // This method will handle the updating of professors
        });

    }

    private void updateInstructorComboBox(String department) {
        List<String> instructors = instructorFileManager.getInstructorsByDepartment(department);
        instructorComboBox.setItems(FXCollections.observableArrayList(instructors));
    }

    public GridPane createForm(String formTitle) {
        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);

        Label titleLabel = new Label(formTitle);
        titleLabel.setFont(new Font("Arial", 18));
        GridPane.setHalignment(titleLabel, HPos.CENTER);
        GridPane.setColumnSpan(titleLabel, 2);

        formPane.add(titleLabel, 0, 0);
        formPane.add(new Label("Course Name:"), 0, 1);
        formPane.add(courseNameField, 1, 1);
        formPane.add(new Label("Course Description:"), 0, 2);
        formPane.add(courseDescriptionField, 1, 2);
        formPane.add(new Label("Department:"), 0, 3);
        formPane.add(departmentComboBox, 1, 3);
        formPane.add(new Label("Instructor:"), 0, 4);
        formPane.add(instructorComboBox, 1, 4);

        if (!actionButton.getText().isEmpty()) {
            formPane.add(actionButton, 0, 5);
        } else {
            courseDescriptionField.setEditable(false);
            courseNameField.setEditable(false);
        }

        return formPane;
    }

    public void configureActionButton(String label, Consumer<ActionEvent> action) {
        actionButton.setText(label);
        actionButton.setOnAction(action::accept);
    }

    // Getter methods
    public String getCourseName() {
        return courseNameField.getText();
    }

    public String getCourseDescriptionField() {
        return courseDescriptionField.getText();
    }

    public TextField getCourseNameField() {
        return courseNameField;
    }

    public void clearForm() {
        courseNameField.clear();
        courseDescriptionField.clear();
    }

    public String getInstructor() {
        return instructorComboBox.getValue();
    }

    public void prepopulateForm(Course course) {
        courseNameField.setText(course.getName());
        courseDescriptionField.setText(course.getDescription());
        departmentComboBox.setValue(course.getDepartment());
        instructorComboBox.setValue(course.getInstructor());
    }

    public String getDepartment() {
        return departmentComboBox.getValue();
    }
}
