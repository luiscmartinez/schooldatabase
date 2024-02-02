package schooldatabase;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Arrays;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("School Database");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 300);

        // Create a MenuBar and add it to the top of the root pane
        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);

        // Create the "student" menu using MenuFactory
        Menu studentMenu = MenuFactory.createMenu("Student");

        // Create "addStudent" MenuItem with an action
        MenuItem addStudent = MenuFactory.createMenuItem("Add Student", () -> showFormDialog());

        // Create "Edit" MenuItem with an action to show a dialog
        MenuItem editStudent = MenuFactory.createMenuItem("Edit Student", () -> showFormDialog());

        // Create "Exit" MenuItem with an action to close the application
        MenuItem exitItem = MenuFactory.createMenuItem("Exit", () -> primaryStage.close());

        // Add items to the "student" menu
        MenuFactory.addItemsToMenu(studentMenu, Arrays.asList(addStudent, editStudent, exitItem));

        // Add the "student" menu to the menu bar
        menuBar.getMenus().add(studentMenu);

        StudentFormGenerator newStudentForm = new StudentFormGenerator();

        newStudentForm.configureActionButton("Add Student", event -> {
            System.out.println("Form submitted!");
        });

        GridPane formPane = newStudentForm.createForm();

        root.setCenter(formPane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showFormDialog() {
        // Optionally prepopulate the form
        // formGenerator.prepopulateForm("John", "Doe", "123 Main St", "12345", "NY");

    }

    public static void main(String[] args) {
        launch(args);
    }
}
