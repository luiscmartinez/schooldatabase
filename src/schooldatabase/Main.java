package schooldatabase;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static StudentFileManager studentFileManager = new StudentFileManager(
            "/Users/million/codez/cs-213-advanced-java/schooldatabase/src/schooldatabase/students.txt");

    static CourseFileManager courseFileManager = new CourseFileManager(
            "/Users/million/codez/cs-213-advanced-java/schooldatabase/src/schooldatabase/courses.txt");

    static EnrollmentFileManager enrollmentFileManager = new EnrollmentFileManager(
            "/Users/million/codez/cs-213-advanced-java/schooldatabase/src/schooldatabase/enrollments.txt");

    static InstructorFileManager instructorFileManager = new InstructorFileManager(
            "/Users/million/codez/cs-213-advanced-java/schooldatabase/src/schooldatabase/instructors.txt");

    static DepartmentFileManager departmentFileManager = new DepartmentFileManager(
            "/Users/million/codez/cs-213-advanced-java/schooldatabase/src/schooldatabase/departments.txt");

    @Override
    public void start(Stage primaryStage) {
        new MainView(primaryStage, studentFileManager, courseFileManager, enrollmentFileManager, instructorFileManager,
                departmentFileManager);
    }

    public static void main(String[] args) {
        launch(args);
    }
}