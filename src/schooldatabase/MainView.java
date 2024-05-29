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
    private InstructorFileManager instructorFileManager;
    private DepartmentFileManager departmentFileManager;

    public MainView(Stage primaryStage, StudentFileManager studentFileManager, CourseFileManager courseFileManager,
            EnrollmentFileManager enrollmentFileManager, InstructorFileManager instructorFileManager,
            DepartmentFileManager departmentFileManager) {
        this.primaryStage = primaryStage;
        this.studentFileManager = studentFileManager;
        this.courseFileManager = courseFileManager;
        this.enrollmentFileManager = enrollmentFileManager;
        this.instructorFileManager = instructorFileManager;
        this.departmentFileManager = departmentFileManager;
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
        // Instructor Menu
        Menu instructorMenu = MenuFactory.createMenu("Instructor");
        MenuItem addInstructor = MenuFactory.createMenuItem("Add Instructor", () -> {
            showAddInstructorView(root);
        });

        MenuItem editInstructor = MenuFactory.createMenuItem("Edit Instructor", () -> {
            showEditInstructorView(root);
        });

        // Department Menu
        Menu departmentMenu = MenuFactory.createMenu("Department");
        MenuItem addDepartment = MenuFactory.createMenuItem("Add Department", () -> {
            showAddDepartmentView(root);
        });

        MenuItem editDepartment = MenuFactory.createMenuItem("Edit Department", () -> {
            showEditDepartmentView(root);
        });

        MenuFactory.addItemsToMenu(departmentMenu, Arrays.asList(addDepartment, editDepartment));

        MenuFactory.addItemsToMenu(instructorMenu, Arrays.asList(addInstructor, editInstructor));

        MenuFactory.addItemsToMenu(courseMenu, Arrays.asList(addCourse, viewCourse, editCourse));

        // Enrollment Menu
        Menu enrollmentMenu = MenuFactory.createMenu("Enrollment");
        MenuItem addEnrollment = MenuFactory.createMenuItem("Add Enrollment", () -> {
            showAddEnrollmentView(root);
        });
        MenuItem viewEnrollment = MenuFactory.createMenuItem("View Enrollment", () -> {
            showViewEnrollmentView(root);
        });
        MenuItem editEnrollment = MenuFactory.createMenuItem("Edit Enrollment", () -> {
            showEditEnrollmentView(root);
        });
        MenuFactory.addItemsToMenu(enrollmentMenu, Arrays.asList(addEnrollment, viewEnrollment, editEnrollment));

        // Report Menu
        Menu reportMenu = MenuFactory.createMenu("Report");
        MenuItem courseReport = MenuFactory.createMenuItem("Course Report", () -> {
            showGenerateReportView(root);
        });
        MenuFactory.addItemsToMenu(reportMenu, Arrays.asList(courseReport));

        menuBar.getMenus().add(studentMenu);
        menuBar.getMenus().add(courseMenu);
        menuBar.getMenus().add(enrollmentMenu);
        menuBar.getMenus().add(instructorMenu);
        menuBar.getMenus().add(departmentMenu);
        menuBar.getMenus().add(reportMenu);
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
        AddCourseView addCourseView = new AddCourseView(courseFileManager, departmentFileManager,
                instructorFileManager);
        root.setCenter(addCourseView.getFormPane());
    }

    private void showCourseView(BorderPane root) {
        ShowCourseView showCourseView = new ShowCourseView(courseFileManager, departmentFileManager,
                instructorFileManager);
        root.setCenter(showCourseView.getFormPane());
    }

    private void showEditCourseView(BorderPane root) {
        EditCourseView editCourseView = new EditCourseView(courseFileManager, departmentFileManager,
                instructorFileManager);
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

    private void showEditEnrollmentView(BorderPane root) {
        EditEnrollmentView editEnrollmentView = new EditEnrollmentView(enrollmentFileManager, studentFileManager,
                courseFileManager);
        root.setCenter(editEnrollmentView.getFormPane());
    }

    private void showAddInstructorView(BorderPane root) {
        AddInstructorView addInstructorView = new AddInstructorView(instructorFileManager, departmentFileManager);
        root.setCenter(addInstructorView.getFormPane());
    }

    private void showEditInstructorView(BorderPane root) {
        EditInstructorView editInstructorView = new EditInstructorView(instructorFileManager, departmentFileManager);
        root.setCenter(editInstructorView.getFormPane());
    }

    private void showAddDepartmentView(BorderPane root) {
        AddDepartmentView addDepartmentView = new AddDepartmentView(departmentFileManager);
        root.setCenter(addDepartmentView.getFormPane());
    }

    private void showEditDepartmentView(BorderPane root) {
        EditDepartmentView editDepartmentView = new EditDepartmentView(departmentFileManager);
        root.setCenter(editDepartmentView.getFormPane());
    }

    private void showGenerateReportView(BorderPane root) {
        GenerateReportView generateReportView = new GenerateReportView(enrollmentFileManager, studentFileManager,
                courseFileManager, instructorFileManager, departmentFileManager);
        root.setCenter(generateReportView.getFormPane());
    }

}
