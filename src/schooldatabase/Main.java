package schooldatabase;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
        static StudentFileManager studentFileManager;
        static CourseFileManager courseFileManager;
        static EnrollmentFileManager enrollmentFileManager;
        static InstructorFileManager instructorFileManager;
        static DepartmentFileManager departmentFileManager;

        static {
                try {
                        studentFileManager = new StudentFileManager();
                        courseFileManager = new CourseFileManager();
                        enrollmentFileManager = new EnrollmentFileManager(
                                        "/Users/million/codez/cs-213-advanced-java/schooldatabase/src/schooldatabase/enrollments.txt");
                        instructorFileManager = new InstructorFileManager();
                        departmentFileManager = new DepartmentFileManager();
                } catch (EmptyFieldException | IOException e) {
                        // Handle the exceptions here
                        e.printStackTrace();
                }
        }

        @Override
        public void start(Stage primaryStage) {
                new MainView(primaryStage, studentFileManager, courseFileManager, enrollmentFileManager,
                                instructorFileManager,
                                departmentFileManager);
        }

        public static void main(String[] args) {
                launch(args);
        }
}