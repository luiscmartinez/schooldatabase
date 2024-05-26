package schooldatabase;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class DepartmentFormGenerator {
    private TextField name;
    private Button actionButton;

    public DepartmentFormGenerator() {
        this.name = new TextField();
        actionButton = new Button();
    }

    public GridPane createForm(String formTitle) {
        GridPane formPane = new GridPane();

        Label titleLabel = new Label(formTitle);
        titleLabel.setFont(new Font("Arial", 18));
        GridPane.setHalignment(titleLabel, HPos.CENTER);
        GridPane.setColumnSpan(titleLabel, 2);
        formPane.add(titleLabel, 0, 0);
        formPane.add(new Label("Department Name:"), 0, 1);
        formPane.add(name, 1, 1);
        if (!actionButton.getText().isEmpty()) {
            formPane.add(actionButton, 1, 2);
        } else {
            name.setEditable(false);
        }
        return formPane;
    }

    public void prepopulateForm(String name) {
        this.name.setText(name);
    }

    public void configureActionButton(String buttonText, Runnable handler) {
        actionButton.setText(buttonText);
        actionButton.setOnAction(event -> handler.run());
    }

    public String getName() {
        return name.getText();
    }

    public void configureActionButton(String label, Consumer<ActionEvent> action) {
        actionButton.setText(label);
        actionButton.setOnAction(action::accept);
    }

    public void clearForm() {
        name.clear();
    }
}
