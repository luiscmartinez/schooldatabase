package schooldatabase;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import schooldatabase.model.Course;

public class ShowCourseView {
    private CourseFileManager courseFileManager;
    private DepartmentFileManager departmentFileManager;
    private InstructorFileManager instructorFileManager;
    private GridPane formPane;

    public ShowCourseView(CourseFileManager courseFileManager, DepartmentFileManager departmentFileManager) {
        this.courseFileManager = courseFileManager;
        this.departmentFileManager = departmentFileManager;
        this.instructorFileManager = instructorFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        GridPane formPane = new GridPane();
        Label inputLabel = new Label("Course ID");
        TextField courseIdInput = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        Course course;
                        try {
                            course = courseFileManager.getCourse(Integer.parseInt(courseIdInput.getText()));
                            CourseFormGenerator courseForm = new CourseFormGenerator(departmentFileManager,
                                    instructorFileManager);
                            courseForm.prepopulateForm(course);
                            formPane.add(courseForm.createForm("View Course Read Only Form"), 0, 1);
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
        formPane.add(courseIdInput, 1, 0);
        formPane.add(searchButton, 2, 0);
        return formPane;
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
