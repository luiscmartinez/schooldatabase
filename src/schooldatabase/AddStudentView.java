package schooldatabase;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.Arrays;

public class AddStudentView {
    private Stage primaryStage;
    private StudentFileManager studentFileManager;

    public AddStudentView(Stage primaryStage, StudentFileManager studentFileManager) {
        this.primaryStage = primaryStage;
        this.studentFileManager = studentFileManager;
        initializeView();
    }

    private void initializeView() {
        primaryStage.setTitle("School Database");

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);

        // MenuBar setup
        MenuBar menuBar = new MenuBar();
        root.setTop(menuBar);

        // Student Menu
        Menu studentMenu = MenuFactory.createMenu("Student");
        MenuItem addStudent = MenuFactory.createMenuItem("Add Student", this::showFormDialog);
        MenuItem editStudent = MenuFactory.createMenuItem("Edit Student", this::showFormDialog);
        MenuItem exitItem = MenuFactory.createMenuItem("Exit", primaryStage::close);
        MenuFactory.addItemsToMenu(studentMenu, Arrays.asList(addStudent, editStudent, exitItem));
        menuBar.getMenus().add(studentMenu);

        // Form setup
        StudentFormGenerator newStudentForm = new StudentFormGenerator();
        StudentActionHandler actionHandler = new StudentActionHandler(newStudentForm, studentFileManager);
        newStudentForm.configureActionButton("Add Student", event -> actionHandler.handleAddStudent());

        // Add form to the scene
        root.setCenter(newStudentForm.createForm());

        primaryStage.show();
    }

    private void showFormDialog() {
        // Implementation for showing the form dialog
    }
}
