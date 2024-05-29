package schooldatabase;

import java.io.IOException;

import javafx.scene.layout.GridPane;
import schooldatabase.model.Student;

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

        newEnrollmentForm.configureSearchButton(event -> {
            int studentId = Integer.parseInt(newEnrollmentForm.getStudentIdField().getText());
            Student student;
            try {
                student = studentFileManager.getStudent(studentId);
                String studentFullName = student.getFirstName() + " " + student.getLastName();
                newEnrollmentForm.setStudentNameField(studentFullName);
            } catch (EmptyFieldException e) {
                // ! TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

        return newEnrollmentForm.createFormPane("Add Enrollment Form");
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
