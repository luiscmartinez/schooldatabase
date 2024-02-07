package schooldatabase;

import javafx.scene.Scene;
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

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setupMenuBar(BorderPane root) {
        MenuBar menuBar = new MenuBar();

        // Student Menu
        Menu studentMenu = MenuFactory.createMenu("Student");
        MenuItem addStudent = MenuFactory.createMenuItem("Add Student", () -> {
            showAddStudentForm(root);
        });
        MenuItem editStudent = MenuFactory.createMenuItem("Edit Student", () -> {
            /* Action Here */});
        MenuItem exitItem = MenuFactory.createMenuItem("Exit", primaryStage::close);
        MenuFactory.addItemsToMenu(studentMenu, Arrays.asList(addStudent, editStudent, exitItem));

        menuBar.getMenus().add(studentMenu);
        root.setTop(menuBar);
    }

    private void showAddStudentForm(BorderPane root) {
        AddStudentView addStudentView = new AddStudentView(studentFileManager);
        root.setCenter(addStudentView.getFormPane());
    }
}
