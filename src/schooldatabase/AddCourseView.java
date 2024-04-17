package schooldatabase;

import javafx.scene.layout.GridPane;

public class AddCourseView {
    private CourseFileManager courseFileManager;
    private DepartmentFileManager departmentFileManager;
    private InstructorFileManager instructorFileManager;
    private GridPane formPane;

    public AddCourseView(CourseFileManager courseFileManager, DepartmentFileManager departmentFileManager,
            InstructorFileManager instructorFileManager) {
        this.courseFileManager = courseFileManager;
        this.departmentFileManager = departmentFileManager;
        this.instructorFileManager = instructorFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        // Initialize and configure the CourseFormGenerator
        CourseFormGenerator newCourseForm = new CourseFormGenerator(departmentFileManager, instructorFileManager);
        CourseActionHandler actionHandler = new CourseActionHandler(newCourseForm, courseFileManager);
        newCourseForm.configureActionButton("Add Course", event -> actionHandler.handleAddCourse());

        return newCourseForm.createForm("New Course Form");
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
