package schooldatabase;

import schooldatabase.model.Course;

public class CourseActionHandler {
    private final CourseFormGenerator courseForm;
    private final CourseFileManager courseFileManager;

    public CourseActionHandler(CourseFormGenerator courseForm, CourseFileManager courseFileManager) {
        this.courseForm = courseForm;
        this.courseFileManager = courseFileManager;
    }

    public void handleAddCourse() {
        String courseName = courseForm.getCourseNameField().getText();
        String courseDescription = courseForm.getCourseDescriptionField();
        final int courseID = courseFileManager.courses.size() + 1;
        Course newCourse = new Course(courseID, courseName, courseDescription);
        try {
            if (courseFileManager.addCourse(newCourse)) {
                System.out.println("Course added successfully!");
            } else {
                System.out.println("Failed to add course!");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
