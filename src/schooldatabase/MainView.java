package schooldatabase;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.Arrays;

public class MainView {
    private Stage primaryStage;
    private StudentFileManager studentFileManager;

    public MainView(Stage primaryStage, StudentFileManager studentFileManager) {
        this.primaryStage = primaryStage;
        this.studentFileManager = studentFileManager;
        initializeView();
    }

    private void initializeView() {
        primaryStage.setTitle("School Database");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 300);

        // Setup MenuBar
        setupMenuBar(root);

        // Display welcome message
        setupGetStartedButton(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupMenuBar(BorderPane root) {
        MenuBar menuBar = new MenuBar();

        // Student Menu
        Menu studentMenu = MenuFactory.createMenu("Student");
        MenuItem addStudent = MenuFactory.createMenuItem("Add Student", () -> {
            showAddStudentView(root);
        });
        MenuItem viewStudent = MenuFactory.createMenuItem("View Student", () -> {
            // showViewStudentView(root);
        });
        MenuItem editStudent = MenuFactory.createMenuItem("Edit Student", () -> {
            showEditStudentView(root);
        });
        MenuItem exitItem = MenuFactory.createMenuItem("Exit", primaryStage::close);
        MenuFactory.addItemsToMenu(studentMenu, Arrays.asList(addStudent, viewStudent, editStudent, exitItem));

        menuBar.getMenus().add(studentMenu);
        root.setTop(menuBar);
    }

    private void showAddStudentView(BorderPane root) {
        AddStudentView addStudentView = new AddStudentView(studentFileManager);
        root.setCenter(addStudentView.getFormPane());
    }

    private void setupGetStartedButton(BorderPane root) {
        Button getStartedButton = new Button("Get Started");
        getStartedButton.setOnAction(event -> showAddStudentView(root));
        root.setCenter(getStartedButton);
    }

    private void showEditStudentView(BorderPane root) {
        EditStudentView editStudentView = new EditStudentView(studentFileManager);
        root.setCenter(editStudentView.getFormPane());
    }
}
