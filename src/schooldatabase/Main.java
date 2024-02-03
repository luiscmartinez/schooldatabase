package schooldatabase;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
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
            System.out.println("First Name: " + newStudentForm.getFirstName());
            System.out.println("Last Name: " + newStudentForm.getLastName());
            final int studentID = s.students.size() + 1;
            System.out.println("studentID: " + studentID);
            String firstName = newStudentForm.getFirstName();
            String lastName = newStudentForm.getLastName();
            String address = newStudentForm.getAddress();
            String city = newStudentForm.getCity();
            String zipcode = newStudentForm.getZipcode();
            String state = newStudentForm.getState();
            try {
                if (s.addStudent(studentID, firstName, lastName, address, city, zipcode, state)) {
                    System.out.println("Student added successfully!");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,
                            "Student ID: " + studentID + "\nStudent Name: " + firstName + " " + lastName + "\nAddress: "
                                    + address + "\n\t   " + city + ", " + state + " " + zipcode,
                            ButtonType.OK);
                    alert.setHeaderText("Student Added");
                    newStudentForm.clearForm();
                    alert.showAndWait();
                } else {
                    // ! I don't think this make sense because when will the user be able to add
                    // another student with the same id?
                    System.out.println("Failed to add student!");
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Student Already Exists ", ButtonType.OK);
                    alert.showAndWait();
                }
            } catch (EmptyFieldException EFE) {
                Alert alert = new Alert(Alert.AlertType.ERROR, EFE.getMessage(), ButtonType.OK);
                alert.showAndWait();
            } catch (IOException ioe) {
                System.out.println("Error: " + ioe.getMessage());
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
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
