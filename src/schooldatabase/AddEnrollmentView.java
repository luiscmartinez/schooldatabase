package schooldatabase;

import javafx.scene.layout.GridPane;

public class AddEnrollmentView {

    private EnrollmentFileManager enrollmentFileManager;
    private StudentFileManager studentFileManager;
    private CourseFileManager courseFileManager;
    private GridPane formPane;

    public AddEnrollmentView(EnrollmentFileManager enrollmentFileManager, StudentFileManager studentFileManager,
            CourseFileManager courseFileManager) {
        this.enrollmentFileManager = enrollmentFileManager;
        this.studentFileManager = studentFileManager;
        this.courseFileManager = courseFileManager;
        this.formPane = createFormPane();
    }

    private GridPane createFormPane() {
        EnrollmentFormGenerator newEnrollmentForm = new EnrollmentFormGenerator();
        EnrollmentActionHandler actionHandler = new EnrollmentActionHandler(newEnrollmentForm, enrollmentFileManager,
                studentFileManager, courseFileManager);

        newEnrollmentForm.configureActionButton("submit enrollment", event -> actionHandler.handleAddEnrollment());

        return newEnrollmentForm.createFormPane("Add Enrollment Form");
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
