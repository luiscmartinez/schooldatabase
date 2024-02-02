package schooldatabase;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
    // Create the Message Label
    Label messageLbl = new Label("Press any Menu Item to see the message");

    @Override
    public void start(Stage stage) throws Exception {
        // create some menus
        Menu studentMenu = new Menu("Student");
        Menu courseMenu = new Menu("Course");
        // Create the student MenuItems
        MenuItem addStudentItem = new MenuItem("Add Student");
        MenuItem editStudentItem = new MenuItem("Edit Student");
        MenuItem viewStudentItem = new MenuItem("View Student");
        // Create the course MenuItems
        MenuItem addCourseItem = new MenuItem("Add Course");
        MenuItem editCourseItem = new MenuItem("Edit Course");
        MenuItem viewCourseItem = new MenuItem("View Course");
        addStudentItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Add Student");
                // printMessage("You have pressed the New Menu Item");
                // CreateAddStudentScene(primaryStage);
            }
        });
        studentMenu.getItems().addAll(addStudentItem, editStudentItem, viewStudentItem);
        courseMenu.getItems().addAll(addCourseItem, editCourseItem, viewCourseItem);
        stage.setTitle("School Database");
        // Create a menu bar
        MenuBar menuBar = new MenuBar();
        // Add menus to a menu bar
        menuBar.getMenus().addAll(studentMenu, courseMenu);
        // Create the Menu Box
        HBox menu = new HBox();
        // Add the MenuBar to the Menu Box
        menu.getChildren().add(menuBar);

        // Create the VBox
        VBox root = new VBox();
        // Add the children to the VBox
        root.getChildren().addAll(menu, messageLbl);
        // Set the Size of the VBox
        root.setMinSize(350, 250);

        /*
         * Set the padding of the VBox
         * Set the border-style of the VBox
         * Set the border-width of the VBox
         * Set the border-insets of the VBox
         * Set the border-radius of the VBox
         * Set the border-color of the VBox
         */
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        // Create the Scene
        Scene scene = new Scene(root);
        // Add the scene to the Stage
        stage.setScene(scene);
        // Set the title of the Stage
        stage.setTitle("School Database");
        // Display the Stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    static void CreateAddStudentScene(Stage primaryStage) {

    }
}