package schooldatabase;

import java.io.IOException;

public class EnrollmentActionHandler {
    private final EnrollmentFormGenerator newEnrollmentForm;
    private final EnrollmentFileManager enrollmentFileManager;
    private final StudentFileManager studentFileManager;
    private final CourseFileManager courseFileManager;

    public EnrollmentActionHandler(EnrollmentFormGenerator newEnrollmentForm,
            EnrollmentFileManager enrollmentFileManager,
            StudentFileManager studentFileManager, CourseFileManager courseFileManager) {
        this.newEnrollmentForm = newEnrollmentForm;
        this.enrollmentFileManager = enrollmentFileManager;
        this.studentFileManager = studentFileManager;
        this.courseFileManager = courseFileManager;
    }

    public void handleAddEnrollment() throws EmptyFieldException, IOException {
        String year = newEnrollmentForm.getYearField().getText();
        String semester = newEnrollmentForm.getSemesterComboBox();
        char grade = newEnrollmentForm.getGradeComboBox();
        // need to check if student and course id are valid
        int courseId = Integer.parseInt(newEnrollmentForm.getCourseIdField().getText());
        int studentId = Integer.parseInt(newEnrollmentForm.getStudentIdField().getText());

        // Validate student ID
        if (studentFileManager.getStudent(studentId) == null) {
            throw new EmptyFieldException("Student ID does not exist");
        }

        // Validate course ID without re-parsing
        if (courseFileManager.getCourse(courseId) == null) {
            throw new EmptyFieldException("Course ID does not exist");
        }

        // Assuming all validations passed, proceed to add the enrollment
        enrollmentFileManager.addEnrollment(courseId, studentId, year, semester, grade);
    }

    public void handleUpdateEnrollment(int enrollmentId) throws EmptyFieldException, IOException {
        String year = newEnrollmentForm.getYearField().getText();
        String semester = newEnrollmentForm.getSemesterComboBox();
        char grade = newEnrollmentForm.getGradeComboBox();
        // need to check if student and course id are valid
        int courseId = Integer.parseInt(newEnrollmentForm.getCourseIdField().getText());
        int studentId = Integer.parseInt(newEnrollmentForm.getStudentIdField().getText());

        // Validate student ID
        if (studentFileManager.getStudent(studentId) == null) {
            throw new EmptyFieldException("Student ID does not exist");
        }

        // Validate course ID without re-parsing
        if (courseFileManager.getCourse(courseId) == null) {
            throw new EmptyFieldException("Course ID does not exist");
        }

        // Assuming all validations passed, proceed to add the enrollment
        enrollmentFileManager.updateEnrollment(enrollmentId, studentId, courseId, year, semester, grade);
    }
}