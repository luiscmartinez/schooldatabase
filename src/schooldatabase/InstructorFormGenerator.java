package schooldatabase;

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

    public InstructorFormGenerator(DepartmentFileManager departmentFileManager) {
        name = new TextField();
        actionButton = new Button();
        departmentComboBox = new ComboBox<>(
                FXCollections.observableArrayList(departmentFileManager.getDepartmentNames()));
    }

    public GridPane createForm() {
        GridPane formPane = new GridPane();
        formPane.setHgap(10);
        formPane.setVgap(10);
        Label titleLabel = new Label("Add Instructor");
        titleLabel.setFont(new Font("Arial", 18));
        GridPane.setHalignment(titleLabel, HPos.CENTER);
        GridPane.setColumnSpan(titleLabel, 2);

        formPane.add(new Label("Instructor Name"), 0, 0);
        formPane.add(name, 1, 0);
        formPane.add(new Label("Department"), 0, 1);
        formPane.add(departmentComboBox, 1, 1);
        actionButton.setText("Add Instructor");
        formPane.add(actionButton, 1, 2);

        return formPane;
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
