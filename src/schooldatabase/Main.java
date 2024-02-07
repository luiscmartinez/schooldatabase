package schooldatabase;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static StudentFileManager studentFileManager = new StudentFileManager(
            "/Users/million/codez/cs-213-advanced-java/schooldatabase/src/schooldatabase/students.txt");

    @Override
    public void start(Stage primaryStage) {
        new MainView(primaryStage, studentFileManager);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
