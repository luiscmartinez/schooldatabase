package schooldatabase;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import schooldatabase.model.Course;

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
        final int courseID = courseFileManager.courses.size() + 1;
        Course newCourse = new Course(courseID, courseName, courseDescription, courseForm.getDepartment(),
                courseForm.getInstructor());
        try {
            if (courseFileManager.addCourse(newCourse)) {
                System.out.println("Course added successfully!");
                Alert alert = new Alert(Alert.AlertType.NONE, "Course ID: " + courseID + "\nCourse Name: " + courseName
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
