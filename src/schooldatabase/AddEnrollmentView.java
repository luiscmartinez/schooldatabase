package schooldatabase;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import schooldatabase.model.Course;
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

        newEnrollmentForm.configureActionButton("submit enrollment", event -> {
            if (newEnrollmentForm.getStudentIdField().getText().isEmpty()
                    || newEnrollmentForm.getCourseIdField().getText().isEmpty()
                    || newEnrollmentForm.getYearField().getText().isEmpty()
                    || newEnrollmentForm.getSemesterComboBox().isEmpty()
                    || newEnrollmentForm.getGradeComboBox() == ' ') {
                Alert alert = new Alert(Alert.AlertType.ERROR, "All fields must be filled out", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            try {
                actionHandler.handleAddEnrollment();
                Alert alert = new Alert(Alert.AlertType.INFORMATION,
                        "Enrollment updated successfully", ButtonType.OK);
                alert.showAndWait();
                newEnrollmentForm.clearForm();
            } catch (EmptyFieldException | IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(),
                        ButtonType.OK);
                alert.showAndWait();
            }
        });

        newEnrollmentForm.configureStudentSearchButton(event -> {
            int studentId = Integer.parseInt(newEnrollmentForm.getStudentIdField().getText());
            Student student;
            student = studentFileManager.getStudent(studentId);
            if (student == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Student was not Found",
                        ButtonType.OK);
                alert.showAndWait();
            } else {
                String studentFullName = student.getFirstName() + " " + student.getLastName();
                newEnrollmentForm.setStudentNameField(studentFullName);
            }
        });

        newEnrollmentForm.configureCourseSearchButton(event -> {
            int courseId = Integer.parseInt(newEnrollmentForm.getCourseIdField().getText());
            Course course;
            course = courseFileManager.getCourse(courseId);
            if (course == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Enrollment was not Found",
                        ButtonType.OK);
                alert.showAndWait();

            } else {
                String courseName = course.getName();
                newEnrollmentForm.setCourseNameField(courseName);
            }

        });

        return newEnrollmentForm.createFormPane("Add Enrollment Form");
    }

    public GridPane getFormPane() {
        return this.formPane;
    }
}
