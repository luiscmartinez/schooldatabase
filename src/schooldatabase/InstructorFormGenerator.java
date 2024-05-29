package schooldatabase;

import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;

public class InstructorFormGenerator {
    private TextField name;
    private ComboBox<String> departmentComboBox;
    private Button actionButton;

    public InstructorFormGenerator(ArrayList<String> departmentNames) {
        name = new TextField();
        actionButton = new Button();
        departmentComboBox = new ComboBox<>(
                FXCollections.observableArrayList(departmentNames));
    }

    public InstructorFormGenerator() {
        name = new TextField();
        actionButton = new Button();
        departmentComboBox = new ComboBox<>();
        departmentComboBox.setEditable(false);
        name.setEditable(false);
    }

    public GridPane createForm(String formName) {
        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);
        Label formTitle = new Label(formName);
        formTitle.setFont(new Font("Arial", 18));
        GridPane.setHalignment(formTitle, HPos.CENTER);
        GridPane.setColumnSpan(formTitle, 2);
        formPane.add(formTitle, 0, 0);
        formPane.add(new Label("Instructor Name"), 0, 1);
        formPane.add(name, 1, 1);
        formPane.add(new Label("Department"), 0, 2);
        formPane.add(departmentComboBox, 1, 2);

        if (!actionButton.getText().isEmpty()) {
            formPane.add(actionButton, 0, 3);
        }
        return formPane;
    }

    public void prepopulateForm(String instructorName, String departmentName) {
        name.setText(instructorName);
        departmentComboBox.setValue(departmentName);
    }

    public void configureActionButton(String buttonText, Consumer<ActionEvent> action) {
        actionButton.setText(buttonText);
        actionButton.setOnAction(action::accept);
    }

    public String getName() {
        return name.getText();
    }

    public String getDepartment() {
        return departmentComboBox.getValue();
    }

    public void clearForm() {
        name.clear();
        departmentComboBox.getSelectionModel().clearSelection();
    }
}
