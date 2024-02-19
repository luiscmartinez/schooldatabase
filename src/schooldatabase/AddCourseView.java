package schooldatabase;

import javafx.scene.layout.GridPane;

public class AddCourseView {
    private CourseFileManager courseFileManager;
    private GridPane formPane;

    public AddCourseView(CourseFileManager courseFileManager) {
        this.courseFileManager = courseFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        // Initialize and configure the CourseFormGenerator
        CourseFormGenerator newCourseForm = new CourseFormGenerator();
        CourseActionHandler actionHandler = new CourseActionHandler(newCourseForm, courseFileManager);
        newCourseForm.configureActionButton("Add Course", event -> actionHandler.handleAddCourse());

        return newCourseForm.createForm("New Course Form");
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
