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

    public GridPane createFormPane() {
        // Initialize and configure the EnrollmentFormGenerator
        EnrollmentFormGenerator newEnrollmentForm = new EnrollmentFormGenerator(enrollmentFileManager,
                studentFileManager, courseFileManager);
        return newEnrollmentForm.getFormPane();
    }
}
