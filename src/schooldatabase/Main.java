package schooldatabase;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static StudentFileManager studentFileManager = new StudentFileManager(
            "/Users/million/codez/cs-213-advanced-java/schooldatabase/src/schooldatabase/students.txt");

    static CourseFileManager courseFileManager = new CourseFileManager(
            "/Users/million/codez/cs-213-advanced-java/schooldatabase/src/schooldatabase/courses.txt");

    @Override
    public void start(Stage primaryStage) {
        new MainView(primaryStage, studentFileManager, courseFileManager);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
