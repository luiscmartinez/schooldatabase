package schooldatabase;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import schooldatabase.model.Course;
import schooldatabase.model.Department;

public class EditCourseView {
    private CourseFileManager courseFileManager;
    private DepartmentFileManager departmentFileManager;
    private InstructorFileManager instructorFileManager;
    private GridPane formPane;

    public EditCourseView(CourseFileManager courseFileManager, DepartmentFileManager departmentFileManager,
            InstructorFileManager instructorFileManager) {
        this.courseFileManager = courseFileManager;
        this.departmentFileManager = departmentFileManager;
        this.instructorFileManager = instructorFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        GridPane formPane = new GridPane();
        Label inputLabel = new Label("Course ID");
        TextField courseIdInput = new TextField();
        courseIdInput.setPromptText("ID");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            int courseId = Integer.parseInt(courseIdInput.getText());
            Course course;
            try {
                course = courseFileManager.getCourse(courseId);
                CourseFormGenerator editCourseForm = new CourseFormGenerator(departmentFileManager,
                        instructorFileManager);
                editCourseForm.prepopulateForm(course);
                editCourseForm.configureActionButton("Update Course", e -> {
                    try {
                        int instructor_id = editCourseForm.getInstructor().getId();
                        int department_id = editCourseForm.getInstructor().getDepartmentID();
                        courseFileManager.updateCourse(course.getCourseID(),
                                editCourseForm.getCourseName(),
                                editCourseForm.getCourseDescriptionField(), department_id, instructor_id);

                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Course Updated", ButtonType.OK);
                        alert.showAndWait();
                    } catch (IOException IOE) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, IOE.getMessage(),
                                ButtonType.OK);
                        alert.showAndWait();
                    } catch (EmptyFieldException EFE) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, EFE.getMessage(),
                                ButtonType.OK);
                        alert.showAndWait();
                    }
                });
                formPane.add(editCourseForm.createForm("Edit Course Form"), 0, 1);
            } catch (EmptyFieldException EFE) {
                Alert alert = new Alert(Alert.AlertType.ERROR, EFE.getMessage(), ButtonType.OK);
                alert.showAndWait();
            } catch (IOException ioe) {
                System.out.println("Error: " + ioe.getMessage());
            } catch (NumberFormatException NFE) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "ID Field was Left Blank", ButtonType.OK);
                alert.showAndWait();
            } catch (Exception exc) {
                Alert alert = new Alert(Alert.AlertType.ERROR, exc.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
        });
        formPane.add(inputLabel, 0, 0);
        formPane.add(courseIdInput, 1, 0);
        formPane.add(searchButton, 2, 0);
        return formPane;
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
