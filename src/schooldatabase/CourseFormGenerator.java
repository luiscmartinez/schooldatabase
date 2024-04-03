package schooldatabase;

import java.util.function.Consumer;

import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import schooldatabase.model.Course;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class CourseFormGenerator {
    private TextField courseNameField;
    private TextField courseDescriptionField;
    private Button actionButton;

    public CourseFormGenerator() {
        // Initialize form components
        courseNameField = new TextField();
        courseDescriptionField = new TextField();
        actionButton = new Button();
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

        if (!actionButton.getText().isEmpty()) {
            formPane.add(actionButton, 0, 3);
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

    public void prepopulateForm(Course course) {
        courseNameField.setText(course.getName());
        courseDescriptionField.setText(course.getDescription());
    }
}
