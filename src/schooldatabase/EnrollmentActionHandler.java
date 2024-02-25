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

    public void handleAddEnrollment() {
        String year = newEnrollmentForm.getYearField().getText();
        String semester = newEnrollmentForm.getSemesterComboBox().getValue();
        char grade = newEnrollmentForm.getGradeComboBox().getValue();
        // need to check if student and course id are valid
        try {
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
            final int enrollmentID = enrollmentFileManager.enrollments.size() + 1;
            enrollmentFileManager.addEnrollment(enrollmentID, courseId, studentId, year, semester, grade);

        } catch (NumberFormatException e) {
            // Handle parsing errors for courseId and studentId
            // Log the error or inform the user
        } catch (EmptyFieldException efe) {
            // Handle missing student or course
            // Log the error or inform the user
        }
        // Consider catching other exceptions if necessary
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}