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
    private CourseFileManager courseFileManager;
    private EnrollmentFileManager enrollmentFileManager;

    public MainView(Stage primaryStage, StudentFileManager studentFileManager, CourseFileManager courseFileManager,
            EnrollmentFileManager enrollmentFileManager) {
        this.primaryStage = primaryStage;
        this.studentFileManager = studentFileManager;
        this.courseFileManager = courseFileManager;
        this.enrollmentFileManager = enrollmentFileManager;
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
            showStudentView(root);
        });
        MenuItem editStudent = MenuFactory.createMenuItem("Edit Student", () -> {
            showEditStudentView(root);
        });
        MenuItem exitItem = MenuFactory.createMenuItem("Exit", primaryStage::close);
        MenuFactory.addItemsToMenu(studentMenu, Arrays.asList(addStudent, viewStudent, editStudent, exitItem));

        // Course Menu
        Menu courseMenu = MenuFactory.createMenu("Course");
        MenuItem addCourse = MenuFactory.createMenuItem("Add Course", () -> {
            showAddCourseView(root);
        });

        MenuItem viewCourse = MenuFactory.createMenuItem("View Course", () -> {
            showCourseView(root);
        });

        MenuItem editCourse = MenuFactory.createMenuItem("Edit Course", () -> {
            showEditCourseView(root);
        });

        MenuFactory.addItemsToMenu(courseMenu, Arrays.asList(addCourse, viewCourse, editCourse));

        // Enrollment Menu
        Menu enrollmentMenu = MenuFactory.createMenu("Enrollment");
        MenuItem addEnrollment = MenuFactory.createMenuItem("Add Enrollment", () -> {
            showAddEnrollmentView(root);
        });
        MenuItem viewEnrollment = MenuFactory.createMenuItem("View Enrollment", () -> {
            showViewEnrollmentView(root);
        });
        MenuFactory.addItemsToMenu(enrollmentMenu, Arrays.asList(addEnrollment, viewEnrollment));

        menuBar.getMenus().add(studentMenu);
        menuBar.getMenus().add(courseMenu);
        menuBar.getMenus().add(enrollmentMenu);
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

    private void showStudentView(BorderPane root) {
        ShowStudentView showStudentView = new ShowStudentView(studentFileManager);
        root.setCenter(showStudentView.getFormPane());
    }

    private void showAddCourseView(BorderPane root) {
        AddCourseView addCourseView = new AddCourseView(courseFileManager);
        root.setCenter(addCourseView.getFormPane());
    }

    private void showCourseView(BorderPane root) {
        ShowCourseView showCourseView = new ShowCourseView(courseFileManager);
        root.setCenter(showCourseView.getFormPane());
    }

    private void showEditCourseView(BorderPane root) {
        EditCourseView editCourseView = new EditCourseView(courseFileManager);
        root.setCenter(editCourseView.getFormPane());
    }

    private void showAddEnrollmentView(BorderPane root) {
        AddEnrollmentView addEnrollmentView = new AddEnrollmentView(enrollmentFileManager, studentFileManager,
                courseFileManager);
        root.setCenter(addEnrollmentView.getFormPane());
    }

    private void showViewEnrollmentView(BorderPane root) {
        ShowEnrollmentView showEnrollmentView = new ShowEnrollmentView(enrollmentFileManager);
        root.setCenter(showEnrollmentView.getFormPane());
    }
}
