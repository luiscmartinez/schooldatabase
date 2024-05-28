package schooldatabase;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class CourseActionHandler {
    private final CourseFormGenerator courseForm;
    private final CourseFileManager courseFileManager;

    public CourseActionHandler(CourseFormGenerator courseForm, CourseFileManager courseFileManager) {
        this.courseForm = courseForm;
        this.courseFileManager = courseFileManager;
    }

    public void handleAddCourse() {
        String courseName = courseForm.getCourseNameField().getText();
        String courseDescription = courseForm.getCourseDescriptionField();
        try {
            if (courseName.isEmpty() || courseDescription.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "One or More Fields Are Empty", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            int departmentID = courseForm.getInstructor().getDepartmentID();
            int instructorID = courseForm.getInstructor().getId();
            System.out.println("course Description: " + courseForm.getCourseDescriptionField()
                    + " course departmentID : " + courseForm.getDepartment());

            if (courseFileManager.addCourse(courseName, courseDescription, departmentID, instructorID)) {
                System.out.println("Course added successfully!");

                Alert alert = new Alert(Alert.AlertType.NONE, "Course ID: " +
                        "\nCourse Name: " + courseName
                        + "\nCourse Description: " + courseDescription, ButtonType.OK);
                alert.setHeaderText("Course Was Added");
                alert.showAndWait();
            } else {
                System.out.println("Failed to add course!");
                Alert alert = new Alert(Alert.AlertType.ERROR, "Course Already Exists ", ButtonType.OK);
                alert.showAndWait();
            }
        } catch (IOException ioe) {
            System.out.println("Error: " + ioe.getMessage());
        } catch (EmptyFieldException EFE) {
            Alert alert = new Alert(Alert.AlertType.ERROR, EFE.getMessage(), ButtonType.OK);
            alert.showAndWait();
        } catch (Exception exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR, exc.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
        courseForm.clearForm();
    }
}
